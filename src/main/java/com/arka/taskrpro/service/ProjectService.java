package com.arka.taskrpro.service;

import com.arka.taskrpro.models.domain.AppUserSummary;
import com.arka.taskrpro.models.domain.ProjectCreateUpdate;
import com.arka.taskrpro.models.dto.AppUserSummaryDto;
import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    public Project createProject(ProjectCreateUpdate project);

    public Page<Project> getProjects(Pageable pageable);

    public List<AppUserSummary> getUsersForProject(Long projectId);

    public Project getProjectDetailsById(Long projectId);

}
