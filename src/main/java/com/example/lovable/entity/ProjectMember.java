package com.example.lovable.entity;


import com.example.lovable.entity.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {
    // Composite primary key consisting of (projectId, userId).
    // The ProjectMemberId object is embedded into this entity and
    // acts as the primary key instead of a single @Id field.
    @EmbeddedId
    private ProjectMemberId id;

    // Many ProjectMember records can belong to one Project.
    // @MapsId("projectId") tells JPA that the projectId field inside
    // the composite key should be populated from project.getId().
    @ManyToOne
    @MapsId("projectId")
    private Project project;

    // Many ProjectMember records can belong to one User.
    // @MapsId("userId") tells JPA that the userId field inside
    // the composite key should be populated from user.getId().
    @ManyToOne
    @MapsId("userId")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

    Instant invitedAt;
    Instant acceptedAt;
}
