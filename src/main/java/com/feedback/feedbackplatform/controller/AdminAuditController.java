package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.model.AuditLog;
import com.feedback.feedbackplatform.repository.AuditLogRepository;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/admin/audit-logs")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAuditController {

    private final AuditLogRepository auditLogRepository;

    public AdminAuditController(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @GetMapping
    public Page<AuditLog> getAuditLogs(
            @RequestParam Optional<String> role,
            @RequestParam Optional<String> action,
            @RequestParam Optional<LocalDateTime> from,
            @RequestParam Optional<LocalDateTime> to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("timestamp").descending()
        );

        if (role.isPresent()) {
            return auditLogRepository.findByRole(role.get(), pageable);
        }

        if (action.isPresent()) {
            return auditLogRepository.findByAction(action.get(), pageable);
        }

        if (from.isPresent() && to.isPresent()) {
            return auditLogRepository.findByTimestampBetween(
                    from.get(),
                    to.get(),
                    pageable
            );
        }

        return auditLogRepository.findAll(pageable);
    }
}
