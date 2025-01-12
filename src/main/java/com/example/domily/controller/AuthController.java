package com.example.domily.controller;

import com.example.domily.entity.User;
import com.example.domily.service.UserService;
import com.example.domily.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(jwtUtil.passwordEncoder().encode(user.getPassword()));
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User client) {
        User u = userService.findByEmail(client.getEmail());
        if (u != null && jwtUtil.passwordEncoder().matches(client.getPassword(), u.getPassword())) {
            String token = jwtUtil.generateToken(u.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
