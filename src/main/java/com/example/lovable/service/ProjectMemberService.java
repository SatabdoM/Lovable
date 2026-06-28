package com.example.lovable.service;

import com.example.lovable.dto.member.InviteMemberRequest;
import com.example.lovable.dto.member.MemberResponse;
import com.example.lovable.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, Long userId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest newRole, Long userId);

    void removeProjectMember(Long projectId, Long memberId, Long userId);
}
