package com.example.lovable.controller;

import com.example.lovable.dto.member.InviteMemberRequest;
import com.example.lovable.dto.member.MemberResponse;
import com.example.lovable.dto.member.UpdateMemberRequest;
import com.example.lovable.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId) {
        Long userId = 1L; // Placeholder for authenticated user ID
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId, @RequestBody InviteMemberRequest request) {
        Long userId = 1L; // Placeholder for authenticated user ID
        return ResponseEntity.ok(projectMemberService.inviteMember(projectId, userId, request));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody UpdateMemberRequest newRole) {
        // Implementation for updating member role
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, newRole, userId));
    }

    //Todo: Delete member API

}
