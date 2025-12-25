package com.feedback.feedbackplatform.dto;

public class CreateFeedbackRequest {

    // ⭐ rating (1–5)
    private int rating;

    // 📝 comment
    private String comment;

    // ---------- getters ----------
    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    // ---------- setters ----------
    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
