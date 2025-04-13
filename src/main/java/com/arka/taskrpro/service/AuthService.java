package com.arka.taskrpro.service;

import com.arka.taskrpro.models.domain.AuthRequest;
import com.arka.taskrpro.models.domain.AuthResponse;
import com.arka.taskrpro.models.dto.AuthResponseDto;

public interface AuthService {
    public AuthResponse login(AuthRequest request);
}
