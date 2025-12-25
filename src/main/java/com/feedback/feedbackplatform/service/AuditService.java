package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.model.AuditLog;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(
            String action,
            String entityType,
            Long entityId,
            User user
    ) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setPerformedBy(user.getEmail());
        log.setRole(user.getRole().name());
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}
