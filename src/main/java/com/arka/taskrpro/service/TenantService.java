package com.arka.taskrpro.service;

import com.arka.taskrpro.models.domain.TenantCreateUpdateRequest;
import com.arka.taskrpro.models.entity.Tenant;

public interface TenantService {
    public Tenant createTenant(TenantCreateUpdateRequest request);
}
