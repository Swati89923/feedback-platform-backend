package com.feedback.feedbackplatform.service;

import com.feedback.feedbackplatform.exception.InvalidCredentialsException;
import com.feedback.feedbackplatform.model.Role;
import com.feedback.feedbackplatform.model.User;
import com.feedback.feedbackplatform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER
    public User registerUser(String name, String email, String password, Role role) {

        userRepository.findByEmail(email).ifPresent(u -> {
            throw new RuntimeException("Email already registered");
        });

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    // LOGIN
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return user;
    }

    // PROFILE
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found with email: " + email));
    }
}
