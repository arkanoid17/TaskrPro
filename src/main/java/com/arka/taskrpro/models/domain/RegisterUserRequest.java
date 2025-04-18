package com.arka.taskrpro.models.domain;

import com.arka.taskrpro.models.dto.TenantDto;
import com.arka.taskrpro.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterUserRequest {
    String employeeId;
    String name;
    String email;
    String password;
    Boolean isActive;
}
