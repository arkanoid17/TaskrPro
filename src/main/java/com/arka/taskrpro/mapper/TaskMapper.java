package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.Task;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task fromDto(TaskDto taskDto);
}
