package com.arka.taskrpro.controller;


import com.arka.taskrpro.mapper.AuthRequestMapper;
import com.arka.taskrpro.mapper.AuthResponseMapper;
import com.arka.taskrpro.models.domain.AuthRequest;
import com.arka.taskrpro.models.domain.AuthResponse;
import com.arka.taskrpro.models.dto.AuthRequestDto;
import com.arka.taskrpro.models.dto.AuthResponseDto;
import com.arka.taskrpro.service.AuthService;
import com.arka.taskrpro.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthRequestMapper authRequestMapper;

    @Autowired
    AuthResponseMapper authResponseMapper;

    @Autowired
    AuthService authService;

    @PostMapping("login")
    ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto requestDto){

        AuthRequest request = authRequestMapper.fromDto(requestDto);

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(authResponseMapper.toDto(response));

    }

}
