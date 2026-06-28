package com.example.lovable.service;

import com.example.lovable.dto.project.ProjectRequest;
import com.example.lovable.dto.project.ProjectResponse;
import com.example.lovable.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getProjectById(Long projectId);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long projectId, ProjectRequest request);

    ProjectResponse softDelete(Long projectId);
}
