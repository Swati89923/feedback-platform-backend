package com.feedback.feedbackplatform.repository;

import com.feedback.feedbackplatform.model.FeedbackComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackCommentRepository
        extends JpaRepository<FeedbackComment, Long> {

    // 🔥 ek feedback ke saare comments
    List<FeedbackComment> findByFeedbackId(Long feedbackId);
}
