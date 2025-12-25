package com.feedback.feedbackplatform.repository;

import com.feedback.feedbackplatform.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    // 🔍 kisi user ka wallet
    Optional<Wallet> findByUserId(Long userId);
}
