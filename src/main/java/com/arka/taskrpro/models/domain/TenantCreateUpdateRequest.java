package com.arka.taskrpro.models.domain;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantCreateUpdateRequest {
    @NotBlank(message = "Name cannot be empty!")
    String name;
}
