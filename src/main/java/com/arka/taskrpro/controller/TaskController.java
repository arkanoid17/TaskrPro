package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.TaskMapper;
import com.arka.taskrpro.models.domain.TaskCreateUpdateObj;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.Task;
import com.arka.taskrpro.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskMapper taskMapper;

    @PostMapping("create")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskCreateUpdateObj request ){
        Task task = taskService.createTask(request);
        return ResponseEntity.ok(taskMapper.toDto(task));
    }
}
