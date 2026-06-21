package com.example.lovable.mapper;

import com.example.lovable.dto.auth.SignupRequest;
import com.example.lovable.dto.auth.UserProfileResponse;
import com.example.lovable.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest request);

    UserProfileResponse toUserProfileResponse(User user);
}
