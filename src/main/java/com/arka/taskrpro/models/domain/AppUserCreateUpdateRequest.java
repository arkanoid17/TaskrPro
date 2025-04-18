package com.arka.taskrpro.models.domain;

import com.arka.taskrpro.models.dto.TenantDto;
import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.validator.ValidRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserCreateUpdateRequest {
    @NotBlank(message ="Employee Id can't be empty!")
    String employeeId;
    @NotBlank(message ="Name can't be empty!")
    String name;
    @NotBlank(message ="Email can't be empty!")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email format"
    )
    String email;
    @NotBlank(message ="Password  can't be empty!")
    String password;
    @ValidRole(enumClass = Role.class,message ="Role can't be empty!")
    Role role;
    Boolean isActive;
}
