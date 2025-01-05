package com.example.domily.controller;

import com.example.domily.entity.Client;
import com.example.domily.service.ClientService;
import com.example.domily.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Client client) {
        // client.setPassword(jwtUtil.passwordEncoder().encode(client.getPassword()));
        clientService.registerClient(client);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Client client) {
        Client user = clientService.findByEmail(client.getEmail());
        if (user != null && jwtUtil.passwordEncoder().matches(client.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
