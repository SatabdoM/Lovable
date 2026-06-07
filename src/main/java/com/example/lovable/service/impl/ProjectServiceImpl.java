package com.example.lovable.service.impl;


import com.example.lovable.dto.project.ProjectRequest;
import com.example.lovable.dto.project.ProjectResponse;
import com.example.lovable.dto.project.ProjectSummaryResponse;
import com.example.lovable.entity.Project;
import com.example.lovable.entity.User;
import com.example.lovable.mapper.ProjectMapper;
import com.example.lovable.repository.ProjectRepository;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;

    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        return List.of();
    }

    @Override
    public ProjectResponse getProjectById(Long projectId, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .build();
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse softDelete(Long projectId, Long userId) {
        return null;
    }
}
