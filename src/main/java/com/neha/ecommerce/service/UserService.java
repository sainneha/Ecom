// --- service/UserService.java ---
package com.neha.ecommerce.service;

import com.neha.ecommerce.model.User;
import com.neha.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepo.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User updateProfile(Long id, User updatedUser) {
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setAddress(updatedUser.getAddress());
        return userRepo.save(user);
    }
}