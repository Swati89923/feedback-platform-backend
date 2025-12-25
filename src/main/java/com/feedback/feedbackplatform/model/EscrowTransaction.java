package com.feedback.feedbackplatform.model;

import jakarta.persistence.*;

@Entity
public class EscrowTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💰 total deposited amount
    private int totalAmount;

    // 💸 amount remaining in escrow
    private int remainingAmount;

    // 🔗 related project
    @OneToOne
    private FeedbackProject project;

    // 👤 developer who deposited
    @ManyToOne
    private User developer;

    // ---------- getters ----------
    public Long getId() {
        return id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public FeedbackProject getProject() {
        return project;
    }

    public User getDeveloper() {
        return developer;
    }

    // ---------- setters ----------
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public void setProject(FeedbackProject project) {
        this.project = project;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }
}
