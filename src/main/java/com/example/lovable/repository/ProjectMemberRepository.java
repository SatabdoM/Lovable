package com.example.lovable.repository;

import com.example.lovable.entity.ProjectMember;
import com.example.lovable.entity.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    List<ProjectMember> findByIdProjectId(Long projectId);

    ///ToDO: Write Query
    Optional<ProjectRole> findRoleByProjectIdAndUserId(Long projectId, Long userId);

    int countProjectOwnedByUser(@Param("userId") Long userId);
}
