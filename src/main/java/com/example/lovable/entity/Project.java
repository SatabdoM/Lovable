package com.example.lovable.entity;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Project {
    Long id;
    String name;
    User owner;
    Boolean isPublic;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
