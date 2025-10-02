package com.luisrocharo.openaiapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisrocharo.openaiapi.dto.LoginUser;
import com.luisrocharo.openaiapi.dto.NewUser;
import com.luisrocharo.openaiapi.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Authentication failed, please check your credentials.");
        }

        try {
            String jwt = authService.authenticate(loginUser.getUsername(), loginUser.getPassword());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Unable to log in now.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Registration failed, please check your credentials.");
        }
        try {
            authService.registerUser(newUser);
            return ResponseEntity.ok().body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Unable to register now: " + e.getMessage());
        }
    }
}
