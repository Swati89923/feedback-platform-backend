package com.feedback.feedbackplatform.repository;

import com.feedback.feedbackplatform.model.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAssignmentRepository
        extends JpaRepository<ProjectAssignment, Long> {

    boolean existsByReviewerIdAndProjectId(Long reviewerId, Long projectId);
}
