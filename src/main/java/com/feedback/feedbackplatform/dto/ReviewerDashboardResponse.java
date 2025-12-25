package com.feedback.feedbackplatform.dto;

public class ReviewerDashboardResponse {

    private int totalFeedbacks;
    private int reviewerScore;
    private int totalEarnings;

    public ReviewerDashboardResponse(
            int totalFeedbacks,
            int reviewerScore,
            int totalEarnings
    ) {
        this.totalFeedbacks = totalFeedbacks;
        this.reviewerScore = reviewerScore;
        this.totalEarnings = totalEarnings;
    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public int getReviewerScore() {
        return reviewerScore;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }
}
