package com.example.domily.controller;

import com.example.domily.entity.Role;
import com.example.domily.entity.User;
import com.example.domily.service.UserService;
import com.example.domily.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // If no role is provided, set a default role
        if (user.getRole() == null) {
            user.setRole(Role.CLIENT); // Set default role to CLIENT
        }

        user.setPassword(jwtUtil.passwordEncoder().encode(user.getPassword()));
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User client) {
        // Find the user by email
        User u = userService.findByEmail(client.getEmail());

        // If user is not found, return "email not found"
        if (u == null) {
            return ResponseEntity.status(401).body("Email not found");
        }

        // If user is found but password doesn't match, return "Incorrect password"
        if (!jwtUtil.passwordEncoder().matches(client.getPassword(), u.getPassword())) {
            return ResponseEntity.status(401).body("Incorrect password");
        }

        // Generate token and return it
        String token = jwtUtil.generateToken(u.getEmail());
        return ResponseEntity.ok(token);
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        User user = userService.findByEmail(email);

        // If user is not found
        if (user == null) {
            return ResponseEntity.status(404).body("Email not found");
        }

        // Update the password
        user.setPassword(jwtUtil.passwordEncoder().encode(newPassword));
        userService.updateUser(user); // Ensure your UserService has an `updateUser` method
        return ResponseEntity.ok("Password updated successfully!");
    }
}
