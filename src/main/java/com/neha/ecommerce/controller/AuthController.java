// --- controller/AuthController.java ---
package com.neha.ecommerce.controller;

import com.neha.ecommerce.dto.LoginRequest;
import com.neha.ecommerce.model.User;
import com.neha.ecommerce.security.JwtUtil;
import com.neha.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail()).orElseThrow();
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("user", user);
            return map;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
} // End