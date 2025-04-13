package com.arka.taskrpro.mapper;

import com.arka.taskrpro.models.domain.AuthRequest;
import com.arka.taskrpro.models.dto.AuthRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthRequestMapper {

    AuthRequest fromDto(AuthRequestDto requestDto);

    AuthRequestDto toDto(AuthRequest request);
}
