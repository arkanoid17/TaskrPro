package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.domain.UserFilters;
import com.arka.taskrpro.models.dto.UserFiltersDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserFilterMapper {
    UserFilters fromDto(UserFiltersDto dto);
    UserFiltersDto toDto(UserFilters filters);
}
