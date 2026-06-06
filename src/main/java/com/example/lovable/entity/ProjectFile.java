package com.example.lovable.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {
    Long id;

    Project project;

    String path;

    String minioObjectKey;

    Instant createdAt;
    Instant updatedAt;

    User createdBy;
    User updatedBy;
}
