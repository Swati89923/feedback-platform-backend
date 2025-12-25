package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.dto.DeveloperDashboardResponse;
import com.feedback.feedbackplatform.dto.ReviewerDashboardResponse;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.service.AnalyticsService;
import com.feedback.feedbackplatform.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final AnalyticsService analyticsService;
    private final UserService userService;

    public DashboardController(
            AnalyticsService analyticsService,
            UserService userService
    ) {
        this.analyticsService = analyticsService;
        this.userService = userService;
    }

    // 👩‍💻 REVIEWER DASHBOARD
    @GetMapping("/reviewer")
    public ReviewerDashboardResponse reviewerDashboard(
            Authentication authentication
    ) {
        String email = authentication.getName();
        User reviewer = userService.getByEmail(email);

        return analyticsService.reviewerDashboard(reviewer);
    }

    // 👨‍💻 DEVELOPER DASHBOARD
    @GetMapping("/developer")
    public DeveloperDashboardResponse developerDashboard(
            Authentication authentication
    ) {
        String email = authentication.getName();
        User developer = userService.getByEmail(email);

        return analyticsService.developerDashboard(developer);
    }
}
