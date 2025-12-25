package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.model.Feedback;
import com.feedback.feedbackplatform.model.FeedbackComment;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.repository.FeedbackCommentRepository;
import com.feedback.feedbackplatform.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackCommentService {

    private final FeedbackCommentRepository commentRepository;
    private final FeedbackRepository feedbackRepository;

    public FeedbackCommentService(
            FeedbackCommentRepository commentRepository,
            FeedbackRepository feedbackRepository
    ) {
        this.commentRepository = commentRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackComment addComment(
            Long feedbackId,
            String message,
            User author
    ) {
        // 1️⃣ feedback exists?
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        // 2️⃣ simple validation
        if (message == null || message.trim().isEmpty()) {
            throw new RuntimeException("Comment cannot be empty");
        }

        // 3️⃣ save comment
        FeedbackComment comment = new FeedbackComment();
        comment.setMessage(message);
        comment.setAuthor(author);
        comment.setFeedback(feedback);

        return commentRepository.save(comment);
    }
}
