package com.feedback.feedbackplatform.dto;

import com.feedback.feedbackplatform.model.Role;
import java.time.LocalDateTime;

public class UserProfileResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private String avatarUrl;
    public UserProfileResponse(Long id,
                               String name,
                               String email,
                               Role role,
                               LocalDateTime createdAt,
                               String avatarUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
}
