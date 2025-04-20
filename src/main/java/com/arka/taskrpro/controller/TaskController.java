package com.arka.taskrpro.controller;

import com.arka.taskrpro.mapper.TaskMapper;
import com.arka.taskrpro.models.domain.TaskCreateUpdateObj;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.Task;
import com.arka.taskrpro.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("list")
    public ResponseEntity<Page<TaskDto>> getTasks(@RequestParam("projectId") Long projectId, Pageable pageable){
        Page<Task> tasks = taskService.getTasks(projectId,pageable);
        return ResponseEntity.ok(taskMapper.getPageDto(tasks));
    }


}
