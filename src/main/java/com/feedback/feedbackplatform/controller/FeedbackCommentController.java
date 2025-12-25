package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.model.FeedbackComment;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.service.FeedbackCommentService;
import com.feedback.feedbackplatform.service.UserService;
import com.feedback.feedbackplatform.repository.FeedbackCommentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback-comments")
public class FeedbackCommentController {

    private final FeedbackCommentService commentService;
    private final UserService userService;
    private final FeedbackCommentRepository commentRepository;

    public FeedbackCommentController(
            FeedbackCommentService commentService,
            UserService userService,
            FeedbackCommentRepository commentRepository
    ) {
        this.commentService = commentService;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    // 🔹 DEV / REVIEWER: add comment on feedback
    @PostMapping("/{feedbackId}")
    public FeedbackComment addComment(
            @PathVariable Long feedbackId,
            @RequestBody String message,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User author = userService.getByEmail(email);

        return commentService.addComment(feedbackId, message, author);
    }

    // 🔹 VIEW all comments for a feedback
    @GetMapping("/{feedbackId}")
    public List<FeedbackComment> getComments(
            @PathVariable Long feedbackId
    ) {
        return commentRepository.findByFeedbackId(feedbackId);
    }
}
