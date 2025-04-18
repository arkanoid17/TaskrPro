package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.TenantMapper;
import com.arka.taskrpro.models.domain.TenantCreateUpdateRequest;
import com.arka.taskrpro.models.dto.TenantDto;
import com.arka.taskrpro.models.entity.Tenant;
import com.arka.taskrpro.service.TenantService;
import com.arka.taskrpro.service.impl.TenantServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tenant")
public class TenantController {

    @Autowired
    TenantMapper tenantMapper;

    @Autowired
    TenantService tenantService;

    @PostMapping("create")
    public ResponseEntity<TenantDto> createTenant(@Valid @RequestBody TenantCreateUpdateRequest request){
        Tenant tenant = tenantService.createTenant(request);
        return ResponseEntity.ok(tenantMapper.toDto(tenant));
    }

}
