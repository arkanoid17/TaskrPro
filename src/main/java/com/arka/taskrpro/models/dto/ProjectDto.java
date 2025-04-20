package com.arka.taskrpro.models.dto;

import com.arka.taskrpro.models.domain.AppUserSummary;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private AppUserSummaryDto createdBy;
    private AppUserSummaryDto manager;
    private List<AppUserSummaryDto> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
