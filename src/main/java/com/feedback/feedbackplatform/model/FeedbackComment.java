package com.feedback.feedbackplatform.model;

import jakarta.persistence.*;

@Entity
public class FeedbackComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💬 comment text
    private String message;

    // 👤 kisne comment kiya (DEV / REVIEWER)
    @ManyToOne
    private User author;

    // 🔗 kis feedback pe
    @ManyToOne
    private Feedback feedback;

    // ---------- getters ----------
    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    // ---------- setters ----------
    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
