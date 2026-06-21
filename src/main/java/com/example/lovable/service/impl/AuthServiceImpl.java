package com.example.lovable.service.impl;

import com.example.lovable.dto.auth.AuthResponse;
import com.example.lovable.dto.auth.LoginRequest;
import com.example.lovable.dto.auth.SignupRequest;

import com.example.lovable.entity.User;
import com.example.lovable.error.BadRequestException;
import com.example.lovable.mapper.UserMapper;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.security.AuthUtil;
import com.example.lovable.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;

    @Override
    public AuthResponse signup(SignupRequest request) {
        //check if user already exists with the same username
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username: " + request.username());
        });
        //Create new user
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        // Generate JWT token
        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadRequestException("Invalid username or password"));

        // Verify password
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException("Invalid username or password");
        }

        // Generate JWT token
        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }
}
