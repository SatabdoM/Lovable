package com.example.lovable.entity;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UsageLog {
    Long id;
    User user;

    Project project;
    Integer tokensUsed;
    Integer durationMs;

    String metaData; // JSON of {model_used
    Instant createdAt;
}
