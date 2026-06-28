package com.example.lovable.mapper;

import com.example.lovable.dto.project.ProjectResponse;
import com.example.lovable.dto.project.ProjectSummaryResponse;
import com.example.lovable.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    //Project Entity to ProjectResponse DTO
    ProjectResponse toProjectResponse(Project project);

    @Mapping(target = "projectName", source = "name")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);
}

