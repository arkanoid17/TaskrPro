package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.AppUserSummaryMapper;
import com.arka.taskrpro.mapper.ProjectMapper;
import com.arka.taskrpro.models.domain.AppUserSummary;
import com.arka.taskrpro.models.domain.ProjectCreateUpdate;
import com.arka.taskrpro.models.dto.AppUserSummaryDto;
import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.entity.Project;
import com.arka.taskrpro.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    AppUserSummaryMapper appUserSummaryMapper;

    @PostMapping("create")
    ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectCreateUpdate project){
        Project prj = projectService.createProject(project);
        return new ResponseEntity<>(projectMapper.toDto(prj), HttpStatus.CREATED);
    }

    @GetMapping("list")
    ResponseEntity<Page<ProjectDto>> getProjects(Pageable pageable){
        Page<Project> projects = projectService.getProjects(pageable);
        return ResponseEntity.ok(projectMapper.toPageUsersDto(projects));
    }

    @GetMapping("id/{projectId}/members")
    ResponseEntity<List<AppUserSummaryDto>> getUsersInProject(@PathVariable Long projectId){
        List<AppUserSummary> users = projectService.getUsersForProject(projectId);
        return ResponseEntity.ok(
                users
                        .stream()
                        .map(user -> appUserSummaryMapper.toDto(user))
                        .toList()
        );
    }

    @GetMapping("id/{projectId}/details")
    ResponseEntity<ProjectDto> getProjectById(@PathVariable Long projectId){
        return ResponseEntity.ok(projectMapper.toDto(projectService.getProjectDetailsById(projectId)));
    }
}
