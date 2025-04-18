package com.arka.taskrpro.models.dto;

import com.arka.taskrpro.models.entity.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantDto {
    Long id;
    String tenant_id;
    String name;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
