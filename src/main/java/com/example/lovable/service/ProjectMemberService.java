package com.example.lovable.service;

import com.example.lovable.dto.member.InviteMemberRequest;
import com.example.lovable.dto.member.MemberResponse;
import com.example.lovable.dto.member.UpdateMemberRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, Long userId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRequest newRole, Long userId);
}
