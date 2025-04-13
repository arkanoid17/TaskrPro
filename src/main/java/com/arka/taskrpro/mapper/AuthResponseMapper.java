package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.domain.AuthResponse;
import com.arka.taskrpro.models.dto.AuthResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthResponseMapper {

    AuthResponse fromDto(AuthResponseDto requestDto);

    AuthResponseDto toDto(AuthResponse response);
}
