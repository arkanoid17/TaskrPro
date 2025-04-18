package com.arka.taskrpro.models.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserSummary {
    long id;
    String name;
}
