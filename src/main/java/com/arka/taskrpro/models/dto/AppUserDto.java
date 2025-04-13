package com.arka.taskrpro.models.dto;

import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.models.entity.Tenant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDto {
    Long id;
    String employeeId;
    String name;
    String email;
    String password;
    private Role role;
    private TenantDto tenant;
    Boolean isActive;
}
