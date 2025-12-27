package com.feedback.feedbackplatform.dto;

import com.feedback.feedbackplatform.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserProfileResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
