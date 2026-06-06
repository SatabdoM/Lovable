package com.example.lovable.entity;


import com.example.lovable.entity.enums.ProjectRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {
    ProjectMemberId id;

    Project project;

    User user;

    ProjectRole projectRole ;

    Instant invitedAt;


}
