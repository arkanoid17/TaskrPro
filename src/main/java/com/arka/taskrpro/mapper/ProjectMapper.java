package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    public ProjectDto toDto(Project project);
    public Project fromDto(ProjectDto dto);

    default Page<ProjectDto> toPageUsersDto(Page<Project> projects) {
        return projects.map(this::toDto);
    }

}
