package com.arka.taskrpro.service.impl;

import com.arka.taskrpro.exceptions.UserException;
import com.arka.taskrpro.models.domain.AppUserCreateUpdateRequest;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.models.entity.Tenant;
import com.arka.taskrpro.repository.AppUserRepository;
import com.arka.taskrpro.repository.TenantRepository;
import com.arka.taskrpro.service.AppUserService;
import io.jsonwebtoken.Claims;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AppUser createUser(AppUserCreateUpdateRequest request, String token) throws UserException {
        token = token.split(" ")[1];
        String tenantId = jwtService.extractClaim(token, claims -> claims.get("tenant-id", String.class));
        Long userId = jwtService.extractClaim(token, claims -> claims.get("user_id", Long.class));


        Optional<AppUser> createUser = appUserRepository.findById(userId);
        if(createUser.isEmpty()){
            throw new UserException("You do not have permission to create User!");
        }

        AppUser creator = createUser.get();

        if(creator.getRole()!= Role.ADMIN){
            throw new UserException("You do not have permission to create User!");
        }

        Optional<Tenant> tenantObj = tenantRepository.findByTenantId(tenantId);
        if(tenantObj.isEmpty()){
            throw new UserException("Cant create user as no tenant info given!");
        }

        Tenant tenant = tenantObj.get();

        AppUser newUser = AppUser
                .builder()
                .tenant(tenant)
                .email(request.getEmail())
                .role(request.getRole())
                .name(request.getName())
                .employeeId(request.getEmployeeId())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(request.getIsActive())
                .build();

        return appUserRepository.save(newUser);

    }

    @Override
    public AppUser findUserById(long userId) {
        return appUserRepository
                .findById(userId)
                .orElseThrow(()->new UserException("Cold not find user!"));
    }
}
