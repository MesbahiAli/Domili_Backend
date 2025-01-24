package com.example.domily.controller;

import com.example.domily.entity.Role;
import com.example.domily.entity.User;
import com.example.domily.service.UserService;
import com.example.domily.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Check if email already exists
            if (userService.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email already registered"));
            }

            // Set default role if none provided
            if (user.getRole() == null) {
                user.setRole(Role.CLIENT);
            }
            
            // Encode password
            String rawPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);
            
            // Save user
            User savedUser = userService.saveUser(user);
            
            return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "userId", savedUser.getId(),
                "email", savedUser.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            System.out.println("Login attempt for email: " + loginRequest.getEmail());
            User user = userService.findByEmail(loginRequest.getEmail());
            System.out.println("Found user: " + (user != null));
            
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Email not found"));
            }
            
            System.out.println("Generating token...");
            String token = jwtUtil.generateToken(user.getEmail());
            System.out.println("Token generated: " + (token != null));
            
            return ResponseEntity.ok(Map.of(
                "token", token,
                "role", user.getRole(),
                "userId", user.getId()
            ));
        } catch (Exception e) {
            e.printStackTrace(); // Add this to see full stack trace
            return ResponseEntity.status(500).body(Map.of("error", "Login failed: " + e.getMessage()));
        }
    }
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String newPassword = request.get("newPassword");
            
            User user = userService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404)
                    .body(Map.of("error", "User not found"));
            }
            
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user);
            
            return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(Map.of("error", "Password update failed: " + e.getMessage()));
        }
    }
}