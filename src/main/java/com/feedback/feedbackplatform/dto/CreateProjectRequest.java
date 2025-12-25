package com.feedback.feedbackplatform.dto;

public class CreateProjectRequest {

    private String projectName;
    private String description;
    private String projectLink;
    private int reviewersNeeded;
    private int payPerReview;

    // 🔹 getters
    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public int getReviewersNeeded() {
        return reviewersNeeded;
    }

    public int getPayPerReview() {
        return payPerReview;
    }

    // 🔹 setters
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public void setReviewersNeeded(int reviewersNeeded) {
        this.reviewersNeeded = reviewersNeeded;
    }

    public void setPayPerReview(int payPerReview) {
        this.payPerReview = payPerReview;
    }
}
