package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserMapper {

    AppUserDto toDto(AppUser user);

    AppUser fromDto(AppUserDto userDto);
}
