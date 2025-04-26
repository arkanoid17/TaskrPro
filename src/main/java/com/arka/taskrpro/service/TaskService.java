package com.arka.taskrpro.service;


import com.arka.taskrpro.models.domain.TaskCreateUpdateObj;
import com.arka.taskrpro.models.domain.TaskFilters;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    public Task createTask(TaskCreateUpdateObj taskCreateUpdateObj);
    public Page<Task> getTasks(Long projectId, TaskFilters taskFilters, Pageable pageable);
}
