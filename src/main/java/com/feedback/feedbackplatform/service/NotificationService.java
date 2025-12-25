package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.model.Notification;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void notify(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);

        repository.save(notification);
    }

    public List<Notification> getMyNotifications(User user) {
        return repository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }
}
