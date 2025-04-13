package com.arka.taskrpro.models.domain;

import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    AppUser user;
    String token;
}
