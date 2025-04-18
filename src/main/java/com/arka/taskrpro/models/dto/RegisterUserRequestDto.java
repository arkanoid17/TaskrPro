package com.arka.taskrpro.models.dto;

import com.arka.taskrpro.models.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterUserRequestDto {
    @NotBlank(message = "Employee Id can't be null")
    String employeeId;

    @NotBlank(message = "Name can't be null")
    String name;

    @NotBlank(message = "Email can't be null")
    String email;

    @NotBlank(message = "Password can't be null")
    String password;

    private Role role;

    Boolean isActive;
}
