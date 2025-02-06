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

import java.util.HashMap;
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
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            if (userService.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Email already registered");
            }
    
            user.setRole(user.getRole() == null ? Role.CLIENT : user.getRole());
            User savedUser = userService.registerUser(user);
    
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedUser.getId());
            response.put("role", savedUser.getRole());
            response.put("message", "User registered successfully!");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Registration failed: " + e.getMessage());
        }
    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User client) {
   try {
       logger.debug("Login attempt for email: {}", client.getEmail());
       User user = userService.findByEmail(client.getEmail());
       
       if (user == null) {
           return ResponseEntity.status(401).body("Email not found");
       }
       
       if (!jwtUtil.verifyPassword(client.getPassword(), user.getPassword())) {
           return ResponseEntity.status(401).body("Incorrect password.");
       }
       
       String token = jwtUtil.generateToken(user.getEmail());
       
       Map<String, Object> response = new HashMap<>();
       response.put("token", token);
       response.put("role", user.getRole());
       response.put("id", user.getId());
       
       return ResponseEntity.ok(response);
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