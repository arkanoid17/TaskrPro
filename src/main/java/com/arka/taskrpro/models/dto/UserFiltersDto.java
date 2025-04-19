package com.arka.taskrpro.models.dto;

import com.arka.taskrpro.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFiltersDto {
    Boolean isActive;
    Role role;
    String search;
}
