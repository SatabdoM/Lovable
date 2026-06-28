package com.example.lovable.service.impl;


import com.example.lovable.dto.project.ProjectRequest;
import com.example.lovable.dto.project.ProjectResponse;
import com.example.lovable.dto.project.ProjectSummaryResponse;
import com.example.lovable.entity.Project;
import com.example.lovable.entity.ProjectMember;
import com.example.lovable.entity.ProjectMemberId;
import com.example.lovable.entity.User;
import com.example.lovable.entity.enums.ProjectRole;
import com.example.lovable.error.ResourceNotFoundException;
import com.example.lovable.mapper.ProjectMapper;
import com.example.lovable.repository.ProjectMemberRepository;
import com.example.lovable.repository.ProjectRepository;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.security.AuthUtil;
import com.example.lovable.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    AuthUtil authUtil;


    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        Long userId = authUtil.getCurrentUserId();

        //Create a proxy object for the owner
        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .build();
        project = projectRepository.save(project);
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole((ProjectRole.OWNER))
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        return projectMapper.toProjectResponse(project);
    }


    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse softDelete(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        projectRepository.delete(project);
        return projectMapper.toProjectResponse(project);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString())
                );
    }
}
