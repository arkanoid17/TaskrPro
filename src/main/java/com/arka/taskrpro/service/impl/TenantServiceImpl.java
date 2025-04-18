package com.arka.taskrpro.service.impl;

import com.arka.taskrpro.exceptions.TokenException;
import com.arka.taskrpro.exceptions.UserException;
import com.arka.taskrpro.models.domain.TenantCreateUpdateRequest;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.models.entity.Tenant;
import com.arka.taskrpro.repository.AppUserRepository;
import com.arka.taskrpro.repository.TenantRepository;
import com.arka.taskrpro.service.TenantService;
import com.arka.taskrpro.utils.AppUtils;
import com.arka.taskrpro.utils.RequestContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public Tenant createTenant(TenantCreateUpdateRequest request) {

        Long userId = RequestContextHolder.getUserId();
        AppUser user = appUserRepository.findById(userId).orElseThrow(()->new UserException("No user found!"));

        if(user.getRole() != Role.ADMIN){
            throw new TokenException("Only admin user can perform this action!");
        }

        Tenant newTenant = tenantRepository.save(
                Tenant
                        .builder()
                        .name(request.getName())
                        .tenant_id(AppUtils.createTenantIdFromName(request.getName()))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .owner(user)
                        .isActive(true)
                        .build()
        );

        user.setTenant(newTenant);


        appUserRepository.save(user);

        return newTenant;
    }
}
