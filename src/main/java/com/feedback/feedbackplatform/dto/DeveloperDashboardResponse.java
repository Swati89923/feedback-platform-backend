package com.feedback.feedbackplatform.dto;

public class DeveloperDashboardResponse {

    private int totalProjects;

    public DeveloperDashboardResponse(int totalProjects, int totalFeedbacks, int totalSpent, double avgRating) {
        this.totalProjects = totalProjects;
    }

    public int getTotalProjects() {
        return totalProjects;
    }
}
