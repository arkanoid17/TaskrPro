package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    // ✅ Correct JPQL and supports pagination
    @Query("SELECT p FROM Project p WHERE p.tenantId = :tenantId")
    Page<Project> findAllProjectsByTenant(@Param("tenantId") String tenantId, Pageable pageable);

    // ✅ Also fixed tenantId reference and added pageable
    @Query("SELECT p FROM Project p JOIN p.members m WHERE m = :member AND p.tenantId = :tenantId")
    Page<Project> findByMemberAndTenant(@Param("member") AppUser member, @Param("tenantId") String tenantId, Pageable pageable);

}
