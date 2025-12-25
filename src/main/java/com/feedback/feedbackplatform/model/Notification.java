package com.feedback.feedbackplatform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean seen = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private User user;

    // getters
    public Long getId() { return id; }
    public String getMessage() { return message; }
    public boolean isSeen() { return seen; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getUser() { return user; }

    // setters
    public void setMessage(String message) { this.message = message; }
    public void setSeen(boolean seen) { this.seen = seen; }
    public void setUser(User user) { this.user = user; }
}
