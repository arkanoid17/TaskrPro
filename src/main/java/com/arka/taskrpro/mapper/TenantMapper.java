package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.dto.TenantDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TenantMapper {

    TenantDto toDto(Tenant tenant);

    Tenant fromDto(TenantDto tenantDto);
}
