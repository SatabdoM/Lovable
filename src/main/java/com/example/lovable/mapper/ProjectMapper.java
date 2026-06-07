package com.example.lovable.mapper;

import com.example.lovable.dto.project.ProjectResponse;
import com.example.lovable.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    //Project Entity to ProjectResponse DTO
    ProjectResponse toProjectResponse(Project project);
}

