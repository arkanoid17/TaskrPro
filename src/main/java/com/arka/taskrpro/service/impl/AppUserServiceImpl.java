package com.arka.taskrpro.service.impl;

import com.arka.taskrpro.exceptions.UserException;
import com.arka.taskrpro.models.domain.AppUserCreateUpdateRequest;
import com.arka.taskrpro.models.domain.UserFilters;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.models.entity.Tenant;
import com.arka.taskrpro.repository.AppUserRepository;
import com.arka.taskrpro.repository.TenantRepository;
import com.arka.taskrpro.service.AppUserService;
import com.arka.taskrpro.utils.RequestContextHolder;
import io.jsonwebtoken.Claims;
import jakarta.persistence.criteria.Predicate;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Page<AppUser> fetchUsers(UserFilters filters, Pageable pageable) {

        String tenantId  = RequestContextHolder.getTenantId();

        Tenant tenant = tenantRepository.findByTenantId(tenantId).orElseThrow(()->new UserException("Tenant not specified!"));

        Specification<AppUser> spec = (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();

            if(tenant!=null){
                predicates.add(cb.equal(root.get("tenant").get("id"), tenant.getId()));
            }

            if (filters.getIsActive() != null) {
                predicates.add(cb.equal(root.get("isActive"), filters.getIsActive()));
            }

            if (filters.getRole() != null) {
                predicates.add(cb.equal(root.get("role"), filters.getRole()));
            }

            if (filters.getSearch() != null && !filters.getSearch().isEmpty()) {
                String search = "%" + filters.getSearch().toLowerCase() + "%";
                Predicate nameLike = cb.like(cb.lower(root.get("name")), search);
                Predicate emailLike = cb.like(cb.lower(root.get("email")), search);
                predicates.add(cb.or(nameLike, emailLike));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return appUserRepository.findAll(spec, pageable);
    }

    @Override
    public AppUser updateUser(AppUserCreateUpdateRequest request, Long userId) {

       AppUser user = appUserRepository.findById(userId).orElseThrow(()->new UserException("User not found!"));

        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setEmployeeId(user.getEmployeeId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive(request.getIsActive());
        user.setRole(request.getRole());


        return appUserRepository.save(user);
    }

    @Override
    public AppUser getUserById(Long userId) {
        return appUserRepository.findById(userId).orElseThrow(()->new UserException("User not found!"));
    }
}
