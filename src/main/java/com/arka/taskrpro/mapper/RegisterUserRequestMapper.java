package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.domain.RegisterUserRequest;
import com.arka.taskrpro.models.dto.AppUserDto;
import com.arka.taskrpro.models.dto.RegisterUserRequestDto;
import com.arka.taskrpro.models.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterUserRequestMapper {

    RegisterUserRequestDto toDto(RegisterUserRequest request);

    RegisterUserRequest fromDto(RegisterUserRequestDto requestDto);
}
