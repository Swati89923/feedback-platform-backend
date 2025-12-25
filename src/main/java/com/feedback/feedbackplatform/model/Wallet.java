package com.feedback.feedbackplatform.model;

import jakarta.persistence.*;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💰 current balance
    private int balance;

    // 👤 wallet owner (reviewer)
    @OneToOne
    private User user;

    // ---------- getters ----------
    public Long getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    // ---------- setters ----------
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
