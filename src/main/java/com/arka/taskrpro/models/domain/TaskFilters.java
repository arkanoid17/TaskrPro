package com.arka.taskrpro.models.domain;

import com.arka.taskrpro.models.entity.TaskPriority;
import com.arka.taskrpro.models.entity.TaskStatus;
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
public class TaskFilters {
    private TaskStatus status;
    private TaskPriority priority;
    private List<Long> assignedTo;
    private LocalDateTime assignedDate;
}
