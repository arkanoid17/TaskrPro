package com.arka.taskrpro.service;


import com.arka.taskrpro.models.domain.TaskCreateUpdateObj;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.Task;

public interface TaskService {
    public Task createTask(TaskCreateUpdateObj taskCreateUpdateObj);
}
