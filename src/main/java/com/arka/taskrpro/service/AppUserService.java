package com.arka.taskrpro.service;

import com.arka.taskrpro.models.domain.AppUserCreateUpdateRequest;
import com.arka.taskrpro.models.entity.AppUser;

public interface AppUserService {
    public AppUser createUser(AppUserCreateUpdateRequest request,String token);

    public AppUser findUserById(long userId);
}
