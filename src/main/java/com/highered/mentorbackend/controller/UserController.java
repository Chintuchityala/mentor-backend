package com.highered.mentorbackend.controller;

import com.highered.mentorbackend.model.User;
import com.highered.mentorbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        System.out.println("Login attempt with: " + user);

        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            System.out.println("Invalid request body received.");
            return null;
        }

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            System.out.println("Login successful for: " + existingUser.getEmail());
            return existingUser;
        }

        System.out.println("Login failed: User not found or incorrect password.");
        return null;
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // already exists
        }
        return ResponseEntity.ok(userRepository.save(user));
    }
}
