package com.arka.taskrpro.service;

import com.arka.taskrpro.models.domain.ProjectCreateUpdate;
import com.arka.taskrpro.models.dto.ProjectDto;
import com.arka.taskrpro.models.entity.Project;

public interface ProjectService {

    public Project createProject(ProjectCreateUpdate project);

}
