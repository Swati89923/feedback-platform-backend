package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.model.Notification;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.service.NotificationService;
import com.feedback.feedbackplatform.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(
            NotificationService notificationService,
            UserService userService
    ) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping
    public List<Notification> myNotifications(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getByEmail(email);

        return notificationService.getMyNotifications(user);
    }
}
