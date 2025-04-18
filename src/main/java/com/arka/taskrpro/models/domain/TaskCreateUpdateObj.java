package com.arka.taskrpro.models.domain;

import com.arka.taskrpro.models.entity.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TaskCreateUpdateObj {

    @NotBlank(message = "Title can't be empty!")
    private String title;

    @NotBlank(message = "Description can't be empty!")
    private String description;

    private TaskStatus status;

    @NotNull(message = "Assigned completion date must not be empty!")
    @Future(message = "Assigned completion date must be in the future")
    private LocalDateTime assignedCompleteDate;

    private LocalDateTime actualCompleteDate;

    private List<Long> assignedUserIds;  // Just IDs, not whole user objects

    private Long createdBy;

    private Long updatedBy;

}
