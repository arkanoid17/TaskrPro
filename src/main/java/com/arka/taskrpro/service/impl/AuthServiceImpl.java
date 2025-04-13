package com.arka.taskrpro.service.impl;

import com.arka.taskrpro.exceptions.AuthException;
import com.arka.taskrpro.models.domain.AuthRequest;
import com.arka.taskrpro.models.domain.AuthResponse;
import com.arka.taskrpro.models.dto.AuthResponseDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Tenant;
import com.arka.taskrpro.repository.AppUserRepository;
import com.arka.taskrpro.repository.TenantRepository;
import com.arka.taskrpro.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager manager;

    @Override
    public AuthResponse login(AuthRequest request) {

       try{
           Authentication authentication = manager
                   .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

           if(!authentication.isAuthenticated()){
               throw new AuthException("Bad credentials!");
           }
        }catch (Exception e){
           throw new AuthException(e.getMessage());
       }

        Optional<AppUser> userOp = userRepository.findUserByEmail(request.getEmail());

        if(userOp.isEmpty()) {
            throw new AuthException("No user found with this email!");
        }

        AppUser user = userOp.get();

        return AuthResponse
                .builder()
                .user(user)
                .token(jwtService.generateToken(
                        user.getId(),
                        user.getEmail(),
                        user.getTenant().getTenant_id())
                )
                .build();

    }


}
