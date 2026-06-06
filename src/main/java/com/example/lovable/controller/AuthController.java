package com.example.lovable.controller;

import com.example.lovable.dto.auth.AuthResponse;
import com.example.lovable.dto.auth.LoginRequest;
import com.example.lovable.dto.auth.SignupRequest;
import com.example.lovable.dto.auth.UserProfileResponse;
import com.example.lovable.service.AuthService;
import com.example.lovable.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authservice;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authservice.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authservice.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = 1L; // Placeholder for authenticated user ID
        return ResponseEntity.ok(userService.getProfile(userId));
    }
}
