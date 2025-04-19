package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.ProjectMapper;
import com.arka.taskrpro.models.domain.ProjectCreateUpdate;
import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.entity.Project;
import com.arka.taskrpro.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;

    @PostMapping("create")
    ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectCreateUpdate project){
        Project prj = projectService.createProject(project);
        return new ResponseEntity<>(projectMapper.toDto(prj), HttpStatus.CREATED);
    }
}
