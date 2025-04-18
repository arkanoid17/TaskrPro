package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository  extends JpaRepository<Tenant,Long> {
    @Query("SELECT t FROM Tenant t WHERE t.tenant_id = :tenantId")
    public Optional<Tenant> findByTenantId(@Param("tenantId") String tenantId);

}
