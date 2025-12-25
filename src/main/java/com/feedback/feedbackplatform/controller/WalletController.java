package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.model.Wallet;
import com.feedback.feedbackplatform.service.UserService;
import com.feedback.feedbackplatform.service.WalletService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;

    public WalletController(
            WalletService walletService,
            UserService userService
    ) {
        this.walletService = walletService;
        this.userService = userService;
    }

    // 🔹 REVIEWER: view wallet balance
    @GetMapping
    public Wallet getMyWallet(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getByEmail(email);

        return walletService.getOrCreateWallet(user);
    }
}
