package com.feedback.feedbackplatform.repository;

import com.feedback.feedbackplatform.model.EscrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscrowTransactionRepository
        extends JpaRepository<EscrowTransaction, Long> {

    // 🔍 ek project ka escrow (1 project = 1 escrow)
    Optional<EscrowTransaction> findByProjectId(Long projectId);
}
