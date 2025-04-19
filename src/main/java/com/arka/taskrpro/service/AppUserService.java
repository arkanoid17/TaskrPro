package com.arka.taskrpro.service;

import com.arka.taskrpro.models.domain.AppUserCreateUpdateRequest;
import com.arka.taskrpro.models.domain.UserFilters;
import com.arka.taskrpro.models.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppUserService {

    public AppUser createUser(AppUserCreateUpdateRequest request,String token);

    public AppUser findUserById(long userId);

    public Page<AppUser> fetchUsers(UserFilters filters, Pageable pageable);

    public AppUser updateUser(AppUserCreateUpdateRequest request, Long userId);

    public AppUser getUserById(Long userId);
}
