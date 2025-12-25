package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.dto.CreateProjectRequest;
import com.feedback.feedbackplatform.model.FeedbackProject;
import com.feedback.feedbackplatform.model.ProjectAssignment;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.repository.FeedbackProjectRepository;
import com.feedback.feedbackplatform.repository.ProjectAssignmentRepository;
import org.springframework.stereotype.Service;
import com.feedback.feedbackplatform.service.NotificationService;

import java.util.List;

@Service
public class FeedbackProjectService {

    private final FeedbackProjectRepository repository;
    private final ProjectAssignmentRepository assignmentRepository;
    private final NotificationService notificationService;

    public FeedbackProjectService(
            FeedbackProjectRepository repository,
            ProjectAssignmentRepository assignmentRepository,
            NotificationService notificationService
    ) {
        this.repository = repository;
        this.assignmentRepository = assignmentRepository;
        this.notificationService = notificationService;
    }


    // ✅ CREATE PROJECT (Developer)
    public FeedbackProject createProject(
            CreateProjectRequest request,
            User developer
    ) {
        FeedbackProject project = new FeedbackProject();
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setProjectLink(request.getProjectLink());
        project.setReviewersNeeded(request.getReviewersNeeded());
        project.setPayPerReview(request.getPayPerReview());
        project.setCreatedBy(developer);

        return repository.save(project);
    }

    // ✅ GET AVAILABLE PROJECTS (Reviewer)
    public List<FeedbackProject> getAvailableProjects() {
        return repository.findByReviewersNeededGreaterThan(0);
    }

    // ✅ ASSIGN PROJECT TO REVIEWER
    public String assignProject(Long projectId, User reviewer) {

        FeedbackProject project = repository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (project.getReviewersNeeded() <= 0) {
            throw new RuntimeException("No reviewers needed now");
        }

        boolean alreadyAssigned =
                assignmentRepository.existsByReviewerIdAndProjectId(
                        reviewer.getId(), projectId
                );

        if (alreadyAssigned) {
            throw new RuntimeException("Already selected this project");
        }

        // 🔽 reduce reviewer count
        project.setReviewersNeeded(project.getReviewersNeeded() - 1);
        repository.save(project);

        // 💾 save assignment
        ProjectAssignment assignment = new ProjectAssignment();
        assignment.setReviewer(reviewer);
        assignment.setProject(project);

        assignmentRepository.save(assignment);
        notificationService.notify(
                reviewer,
                "You have been assigned a new project: "
                        + project.getProjectName()
        );

        return "Project assigned successfully";
    }
}
