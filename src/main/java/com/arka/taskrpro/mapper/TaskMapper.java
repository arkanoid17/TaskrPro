package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.Project;
import com.arka.taskrpro.models.entity.Task;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "priority", target = "priority")
    TaskDto toDto(Task task);
    Task fromDto(TaskDto taskDto);

    default Page<TaskDto> getPageDto(Page<Task> tasks){
        return tasks.map(this::toDto);
    }
}
