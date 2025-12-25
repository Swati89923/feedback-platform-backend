package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.dto.CreateFeedbackRequest;
import com.feedback.feedbackplatform.model.*;
import com.feedback.feedbackplatform.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackProjectRepository projectRepository;
    private final ProjectAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final AuditService auditService;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            FeedbackProjectRepository projectRepository,
            ProjectAssignmentRepository assignmentRepository,
            UserRepository userRepository,
            PaymentService paymentService,
            NotificationService notificationService, EmailService emailService,
            AuditService auditService
    ) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.auditService = auditService;
    }


    // =========================
    // SUBMIT FEEDBACK (REVIEWER)
    // =========================
    public Feedback submitFeedback(
            Long projectId,
            CreateFeedbackRequest request,
            User reviewer
    ) {
        FeedbackProject project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        boolean assigned = assignmentRepository
                .existsByReviewerIdAndProjectId(reviewer.getId(), projectId);

        if (!assigned) {
            throw new RuntimeException("You are not assigned to this project");
        }

        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        Feedback feedback = new Feedback();
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment());
        feedback.setReviewer(reviewer);
        feedback.setProject(project);
        feedback.setStatus(FeedbackStatus.PENDING);

        // ⭐ reviewer score update
        reviewer.setReviewerScore(
                reviewer.getReviewerScore() + 10
        );
        userRepository.save(reviewer);
        auditService.log(
                "FEEDBACK_SUBMITTED",
                "FEEDBACK",
                feedback.getId(),
                reviewer
        );

        return feedbackRepository.save(feedback);
    }

    // =========================
    // ADMIN: VIEW FEEDBACKS
    // =========================
    public List<Feedback> getFeedbacksByStatus(FeedbackStatus status) {
        return feedbackRepository.findByStatus(status);
    }

    // =========================
    // ADMIN: APPROVE FEEDBACK
    // =========================
    public void approveFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        if (feedback.getStatus() == FeedbackStatus.APPROVED) {
            throw new RuntimeException("Feedback already approved");
        }

        feedback.setStatus(FeedbackStatus.APPROVED);
        feedbackRepository.save(feedback);

        FeedbackProject project = feedback.getProject();
        User reviewer = feedback.getReviewer();

        // 💸 AUTO PAYMENT
        paymentService.releasePayment(
                project.getId(),
                project.getPayPerReview(),
                reviewer
        );

        // 🔔 NOTIFICATION
        notificationService.notify(
                reviewer,
                "Your feedback for project '"
                        + project.getProjectName()
                        + "' has been approved 🎉"
        );
        emailService.sendEmail(
                reviewer.getEmail(),
                "Feedback Approved",
                "Your feedback for project '"
                        + project.getProjectName()
                        + "' has been approved."
        );
        auditService.log(
                "FEEDBACK_APPROVED",
                "FEEDBACK",
                feedback.getId(),
                reviewer
        );

    }

    // =========================
    // ADMIN: REJECT FEEDBACK
    // =========================
    public void rejectFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        feedback.setStatus(FeedbackStatus.REJECTED);
        feedbackRepository.save(feedback);

        // 🔔 NOTIFICATION
        notificationService.notify(
                feedback.getReviewer(),
                "Your feedback for project '"
                        + feedback.getProject().getProjectName()
                        + "' was rejected ❌"
        );
        auditService.log(
                "FEEDBACK_REJECTED",
                "FEEDBACK",
                feedback.getId(),
                feedback.getReviewer()
        );

    }
}
