package com.feedback.feedbackplatform.model;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    private String comment;

    @Enumerated(EnumType.STRING)
    private FeedbackStatus status = FeedbackStatus.PENDING;

    @ManyToOne
    private User reviewer;

    @ManyToOne
    private FeedbackProject project;

    // getters
    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public FeedbackStatus getStatus() {
        return status;
    }

    public User getReviewer() {
        return reviewer;
    }

    public FeedbackProject getProject() {
        return project;
    }

    // setters
    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public void setProject(FeedbackProject project) {
        this.project = project;
    }
}
