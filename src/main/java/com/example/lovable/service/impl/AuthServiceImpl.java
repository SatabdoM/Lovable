package com.example.lovable.service.impl;

import com.example.lovable.dto.auth.AuthResponse;
import com.example.lovable.dto.auth.LoginRequest;
import com.example.lovable.dto.auth.SignupRequest;
import com.example.lovable.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
