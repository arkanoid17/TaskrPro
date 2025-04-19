package com.arka.taskrpro.models.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateUpdate {

    @NotBlank(message = "Name can not be empty!")
    private String name;

    @NotBlank(message = "Description can not be empty!")
    private String description;

    @NotNull(message = "Please assign a manager to this project!")
    private Long manager;

    private List<Long> members;

}
