package com.feedback.feedbackplatform.repository;

import com.feedback.feedbackplatform.model.Feedback;
import com.feedback.feedbackplatform.model.FeedbackStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // 🔍 admin: pending feedbacks
    List<Feedback> findByStatus(FeedbackStatus status);
    List<Feedback> findByReviewerId(Long reviewerId);
    List<Feedback> findByProjectId(Long projectId);
}
