package com.feedback.feedbackplatform.model;

import jakarta.persistence.*;

@Entity
public class ProjectAssignment {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User reviewer;

    @ManyToOne
    private FeedbackProject project;

    // getters & setters
    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public void setProject(FeedbackProject project) {
        this.project = project;
    }

    public User getReviewer() {
        return reviewer;
    }

    public FeedbackProject getProject() {
        return project;
    }

}
