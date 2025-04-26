package com.arka.taskrpro.models.dto;

import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.TaskPriority;
import com.arka.taskrpro.models.entity.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TaskDto {

    long id;

    String title;

    String description;

    String tenantId;

    TaskStatus status;

    TaskPriority priority;

    LocalDateTime assignedCompleteDate;

    LocalDateTime actualCompleteDate;

    List<AppUserSummaryDto> assignedUsers;

    AppUserSummaryDto createdBy;

    AppUserSummaryDto updatedBy;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Long projectId;

}
