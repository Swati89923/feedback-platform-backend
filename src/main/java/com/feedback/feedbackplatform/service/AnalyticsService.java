package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.dto.DeveloperDashboardResponse;
import com.feedback.feedbackplatform.dto.ReviewerDashboardResponse;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.repository.*;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final FeedbackProjectRepository projectRepository;
    private final FeedbackRepository feedbackRepository;
    private final EscrowTransactionRepository escrowRepository;

    public AnalyticsService(
            FeedbackProjectRepository projectRepository,
            FeedbackRepository feedbackRepository,
            EscrowTransactionRepository escrowRepository
    ) {
        this.projectRepository = projectRepository;
        this.feedbackRepository = feedbackRepository;
        this.escrowRepository = escrowRepository;
    }

    // 👨‍💻 Developer dashboard
    public DeveloperDashboardResponse developerDashboard(User developer) {

        int totalProjects =
                projectRepository.findAll()
                        .stream()
                        .filter(p -> p.getCreatedBy().getId().equals(developer.getId()))
                        .toList()
                        .size();

        int totalFeedbacks =
                feedbackRepository.findAll().size();

        int totalSpent =
                escrowRepository.findAll()
                        .stream()
                        .filter(e -> e.getDeveloper().getId().equals(developer.getId()))
                        .mapToInt(e -> e.getTotalAmount() - e.getRemainingAmount())
                        .sum();

        double avgRating =
                feedbackRepository.findAll()
                        .stream()
                        .mapToInt(f -> f.getRating())
                        .average()
                        .orElse(0.0);

        return new DeveloperDashboardResponse(
                totalProjects,
                totalFeedbacks,
                totalSpent,
                avgRating
        );
    }

    // 🧑‍🔧 Reviewer dashboard
    public ReviewerDashboardResponse reviewerDashboard(User reviewer) {

        int feedbackCount =
                feedbackRepository.findByReviewerId(reviewer.getId()).size();

        int earnings =
                feedbackCount * 100; // simulated (can link escrow later)

        return new ReviewerDashboardResponse(
                feedbackCount,
                reviewer.getReviewerScore(),
                earnings
        );
    }
}
