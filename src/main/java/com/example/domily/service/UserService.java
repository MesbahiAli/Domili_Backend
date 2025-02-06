package com.example.domily.service;

import com.example.domily.entity.Role;
import com.example.domily.entity.User;
import com.example.domily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.save(user); 
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                // Only hash the password if it's changed
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            if (updatedUser.getNom() != null) user.setNom(updatedUser.getNom());
            if (updatedUser.getPrenom() != null) user.setPrenom(updatedUser.getPrenom());
            if (updatedUser.getAge() != null) user.setAge(updatedUser.getAge());
            if (updatedUser.getSexe() != null) user.setSexe(updatedUser.getSexe());
            if (updatedUser.getAbout() != null) user.setAbout(updatedUser.getAbout());
            if (updatedUser.getPhone() != null) user.setPhone(updatedUser.getPhone());
            if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
            if (updatedUser.getSecteur() != null) user.setSecteur(updatedUser.getSecteur());
            if (updatedUser.getAdresse() != null) user.setAdresse(updatedUser.getAdresse());
            if (updatedUser.getWebsite() != null) user.setWebsite(updatedUser.getWebsite());

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

