package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.AppUserMapper;
import com.arka.taskrpro.mapper.UserFilterMapper;
import com.arka.taskrpro.models.domain.AppUserCreateUpdateRequest;
import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.dto.UserFiltersDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AppUserMapper appUserMapper;

    @Autowired
    UserFilterMapper userFilterMapper;

    @Autowired
    AppUserService userService;

    @PostMapping("create")
    ResponseEntity<AppUserDto> createUser(@Valid @RequestBody AppUserCreateUpdateRequest request, @RequestHeader("Authorization") String token){
        AppUser user = userService.createUser(request,token);
        return new ResponseEntity<>(appUserMapper.toDto(user), HttpStatus.CREATED);
    }

    @GetMapping("list")
    ResponseEntity<Page<AppUserDto>> fetchAllUsers(UserFiltersDto filters, Pageable pageable){
        Page<AppUser> users = userService.fetchUsers(userFilterMapper.fromDto(filters),pageable);
        return ResponseEntity.ok(appUserMapper.toPageUsersDto(users));
    }

    @PostMapping("id/{userId}/update")
    ResponseEntity<AppUserDto> update(@PathVariable Long userId,@Valid @RequestBody AppUserCreateUpdateRequest request, @RequestHeader("Authorization") String token){
        AppUser user = userService.updateUser(request,userId);
        return ResponseEntity.ok(appUserMapper.toDto(user));
    }

    @GetMapping("id/{userId}")
    ResponseEntity<AppUserDto> fetchUserById(@PathVariable Long userId){
        AppUser user = userService.getUserById(userId);
        return ResponseEntity.ok(appUserMapper.toDto(user));
    }
}
