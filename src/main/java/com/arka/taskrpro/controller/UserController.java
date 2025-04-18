package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.AppUserMapper;
import com.arka.taskrpro.models.domain.AppUserCreateUpdateRequest;
import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AppUserMapper appUserMapper;

    @Autowired
    AppUserService userService;

    @PostMapping("create")
    ResponseEntity<AppUserDto> createUser(@Valid @RequestBody AppUserCreateUpdateRequest request, @RequestHeader("Authorization") String token){
        AppUser user = userService.createUser(request,token);
        return new ResponseEntity<>(appUserMapper.toDto(user), HttpStatus.CREATED);
    }
}
