package com.example.lovable.service.impl;

import com.example.lovable.dto.auth.UserProfileResponse;
import com.example.lovable.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
