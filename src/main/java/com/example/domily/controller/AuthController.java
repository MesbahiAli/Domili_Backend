package com.example.domily.controller;

import com.example.domily.entity.Role;
import com.example.domily.entity.User;
import com.example.domily.service.UserService;
import com.example.domily.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            logger.debug("Registration attempt for email: {}", user.getEmail());

            if (userService.findByEmail(user.getEmail()) != null) {
                logger.warn("Email already exists: {}", user.getEmail());
                return ResponseEntity.badRequest().body("Email already registered");
            }

            user.setRole(user.getRole() == null ? Role.CLIENT : user.getRole());
            userService.registerUser(user);

            logger.info("User registered successfully: {}", user.getEmail());
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            logger.error("Error during registration: ", e);
            return ResponseEntity.internalServerError().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User client) {
        try {
            logger.debug("Login attempt for email: {}", client.getEmail());

            User user = userService.findByEmail(client.getEmail());
            if (user == null) {
                logger.debug("User not found: {}", client.getEmail());
                return ResponseEntity.status(401).body("Email not found");
            }

            boolean matches = jwtUtil.verifyPassword(client.getPassword(), user.getPassword());

            if (!matches) {
                logger.debug("Invalid password for email: {}", client.getEmail());
                return ResponseEntity.status(401).body("Incorrect password.");
            }

            String token = jwtUtil.generateToken(user.getEmail());
            logger.info("User logged in successfully: {}", user.getEmail());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            logger.error("Login error: ", e);
            return ResponseEntity.internalServerError().body("Login failed: " + e.getMessage());
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        try {
            logger.debug("Password update attempt for email: {}", email);

            User user = userService.findByEmail(email);
            if (user == null) {
                logger.debug("User not found for password update: {}", email);
                return ResponseEntity.status(404).body("Email not found");
            }

            // Encode and update password
            String encodedPassword = jwtUtil.encodePassword(newPassword);
            user.setPassword(encodedPassword);
            userService.updateUser(user);

            logger.info("Password updated successfully for user: {}", email);
            return ResponseEntity.ok("Password updated successfully!");
        } catch (Exception e) {
            logger.error("Error updating password for email: {}", email, e);
            return ResponseEntity.internalServerError().body("Password update failed: " + e.getMessage());
        }
    }
}