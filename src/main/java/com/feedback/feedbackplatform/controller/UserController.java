package com.swati.feedbackplatform.controller;

import com.swati.feedbackplatform.dto.UserProfileResponse;
import com.swati.feedbackplatform.entity.User;
import com.swati.feedbackplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMyProfile(Authentication authentication) {
        String email = authentication.getName(); // JWT se
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(new UserProfileResponse(user));
    }
}
