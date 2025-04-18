package com.arka.taskrpro.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserSummaryDto {
    private long id;
    private String name;
}
