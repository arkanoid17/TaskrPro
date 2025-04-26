package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {

    @Query("SELECT t FROM tasks t WHERE t.project.id = :projectId AND t.tenantId = :tenantId")
    Page<Task> findAllTasksByProjectId(
            @Param("projectId") Long projectId,
            @Param("tenantId") String tenantId,
            Pageable pageable
    );

    @Query("SELECT t FROM tasks t JOIN t.assignedUsers u WHERE t.project.id = :projectId AND u = :user AND t.tenantId = :tenantId")
    Page<Task> findByAssigneeAndProject(
            @Param("user") AppUser user,
            @Param("projectId") Long projectId,
            @Param("tenantId") String tenantId,
            Pageable pageable
    );



}
