package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    public ProjectDto toDto(Project project);
    public Project fromDto(ProjectDto dto);

}
