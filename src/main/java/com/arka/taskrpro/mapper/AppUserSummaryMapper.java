package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.domain.AppUserSummary;
import com.arka.taskrpro.models.dto.AppUserSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserSummaryMapper {
    public AppUserSummary fromDto(AppUserSummaryDto dto);
    public AppUserSummaryDto toDto(AppUserSummary appUserSummary);
}
