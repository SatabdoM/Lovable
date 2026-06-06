package com.example.lovable.service;

import com.example.lovable.dto.auth.AuthResponse;
import com.example.lovable.dto.auth.LoginRequest;
import com.example.lovable.dto.auth.SignupRequest;


public interface AuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
}
