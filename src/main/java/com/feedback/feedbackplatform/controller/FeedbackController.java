package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.dto.CreateFeedbackRequest;
import com.feedback.feedbackplatform.model.Feedback;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.service.FeedbackService;
import com.feedback.feedbackplatform.service.UserService;
import com.feedback.feedbackplatform.repository.FeedbackRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final FeedbackRepository feedbackRepository;

    public FeedbackController(
            FeedbackService feedbackService,
            UserService userService,
            FeedbackRepository feedbackRepository
    ) {
        this.feedbackService = feedbackService;
        this.userService = userService;
        this.feedbackRepository = feedbackRepository;
    }

    // 🔹 REVIEWER: submit feedback
    @PreAuthorize("hasRole('REVIEWER')")
    @PostMapping("/project/{projectId}")
    public Feedback submitFeedback(
            @PathVariable Long projectId,
            @RequestBody CreateFeedbackRequest request,
            Authentication authentication
    ) {
        User reviewer = userService.getByEmail(authentication.getName());
        return feedbackService.submitFeedback(projectId, request, reviewer);
    }

    // 🔹 DEVELOPER: view feedback of a project
    @PreAuthorize("hasRole('DEVELOPER')")
    @GetMapping("/project/{projectId}")
    public List<Feedback> getFeedbackForProject(
            @PathVariable Long projectId
    ) {
        return feedbackRepository.findByProjectId(projectId);
    }
}
