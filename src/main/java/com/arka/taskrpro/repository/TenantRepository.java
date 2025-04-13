package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository  extends JpaRepository<Tenant,Long> {
}
