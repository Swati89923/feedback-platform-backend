package com.feedback.feedbackplatform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log =
            LoggerFactory.getLogger(EmailService.class);

    // 🔔 Abhi console email (safe)
    // 🔜 Later SMTP add kar sakti ho
    public void sendEmail(String to, String subject, String body) {
        log.info("📧 EMAIL SENT");
        log.info("To      : {}", to);
        log.info("Subject : {}", subject);
        log.info("Body    : {}", body);
    }
}
