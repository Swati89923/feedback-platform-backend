package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.dto.RegisterRequest;
import com.feedback.feedbackplatform.dto.LoginRequest;
import com.feedback.feedbackplatform.dto.LoginResponse;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.service.UserService;
import com.feedback.feedbackplatform.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 🔓 REGISTER (PUBLIC)
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
    }

    // 🔓 LOGIN (PUBLIC)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        String token = JwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}
