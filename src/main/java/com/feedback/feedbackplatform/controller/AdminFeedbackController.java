package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.model.Feedback;
import com.feedback.feedbackplatform.model.FeedbackStatus;
import com.feedback.feedbackplatform.service.FeedbackService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/feedbacks")
@PreAuthorize("hasRole('ADMIN')")
public class AdminFeedbackController {

    private final FeedbackService feedbackService;

    public AdminFeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // 📌 ADMIN: get all pending feedbacks
    @GetMapping("/pending")
    public List<Feedback> getPendingFeedbacks() {
        return feedbackService.getFeedbacksByStatus(FeedbackStatus.PENDING);
    }

    // ✅ ADMIN: approve feedback
    @PostMapping("/{feedbackId}/approve")
    public String approveFeedback(@PathVariable Long feedbackId) {
        feedbackService.approveFeedback(feedbackId);
        return "Feedback approved";
    }

    // ❌ ADMIN: reject feedback
    @PostMapping("/{feedbackId}/reject")
    public String rejectFeedback(@PathVariable Long feedbackId) {
        feedbackService.rejectFeedback(feedbackId);
        return "Feedback rejected";
    }
}
