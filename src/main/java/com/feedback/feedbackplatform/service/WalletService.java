package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.model.Wallet;
import com.feedback.feedbackplatform.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // 🔥 get wallet or create if not exists
    public Wallet getOrCreateWallet(User user) {

        return walletRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Wallet wallet = new Wallet();
                    wallet.setUser(user);
                    wallet.setBalance(0);
                    return walletRepository.save(wallet);
                });
    }

    // 💰 credit money to wallet
    public Wallet credit(User user, int amount) {

        if (amount <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        Wallet wallet = getOrCreateWallet(user);
        wallet.setBalance(wallet.getBalance() + amount);

        return walletRepository.save(wallet);
    }

    // 💸 debit money (future use: withdrawal)
    public Wallet debit(User user, int amount) {

        Wallet wallet = getOrCreateWallet(user);

        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - amount);
        return walletRepository.save(wallet);
    }
}
