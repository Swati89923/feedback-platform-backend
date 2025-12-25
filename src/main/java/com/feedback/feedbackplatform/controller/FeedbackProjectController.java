package com.feedback.feedbackplatform.controller;

import com.feedback.feedbackplatform.dto.CreateProjectRequest;
import com.feedback.feedbackplatform.model.FeedbackProject;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.service.FeedbackProjectService;
import com.feedback.feedbackplatform.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class FeedbackProjectController {

    private final FeedbackProjectService service;
    private final UserService userService;

    public FeedbackProjectController(
            FeedbackProjectService service,
            UserService userService
    ) {
        this.service = service;
        this.userService = userService;
    }

    // ✅ CREATE PROJECT (Developer)
    @PostMapping
    public FeedbackProject createProject(
            @RequestBody CreateProjectRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User developer = userService.getByEmail(email);

        return service.createProject(request, developer);
    }

    // ✅ GET AVAILABLE PROJECTS (Reviewer)
    @GetMapping
    public List<FeedbackProject> getAllAvailableProjects() {
        return service.getAvailableProjects();
    }

    // ✅ ASSIGN PROJECT (Reviewer)
    @PostMapping("/{projectId}/assign")
    public String assignProject(
            @PathVariable Long projectId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User reviewer = userService.getByEmail(email);

        return service.assignProject(projectId, reviewer);
    }
}
