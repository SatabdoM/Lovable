package com.example.lovable.service.impl;

import com.example.lovable.dto.member.InviteMemberRequest;
import com.example.lovable.dto.member.MemberResponse;
import com.example.lovable.dto.member.UpdateMemberRoleRequest;
import com.example.lovable.entity.Project;
import com.example.lovable.entity.ProjectMember;
import com.example.lovable.entity.ProjectMemberId;
import com.example.lovable.entity.User;
import com.example.lovable.mapper.ProjectMemberMapper;
import com.example.lovable.repository.ProjectMemberRepository;
import com.example.lovable.repository.ProjectRepository;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        // Check if the user has access to the project
        Project project = getAccessibleProjectById(projectId, userId);

        return projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, Long userId, InviteMemberRequest request) {
        Project project = getAccessibleProjectById(projectId, userId);

//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("Not Allowed");
//        }

        // Find the user of this email
        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        // Check if the invitee is the owner
        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself!");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);

       // Check if projectMemberId already exists ie invitee is already a member
        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException(" This user is already invited");
        }
        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);
        ProjectMember projectMember= projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("Not Allowed");
//        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);
        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException(" Member not found in this project");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }

}
