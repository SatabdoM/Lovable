package com.example.lovable.dto.member;

import com.example.lovable.entity.enums.ProjectRole;

public record UpdateMemberRequest(
        ProjectRole role
) {
}
