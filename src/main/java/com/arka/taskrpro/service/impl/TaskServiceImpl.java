package com.arka.taskrpro.service.impl;


import com.arka.taskrpro.exceptions.TaskException;
import com.arka.taskrpro.models.domain.TaskCreateUpdateObj;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.*;
import com.arka.taskrpro.repository.ProjectRepository;
import com.arka.taskrpro.repository.TaskRepository;
import com.arka.taskrpro.service.AppUserService;
import com.arka.taskrpro.service.ProjectService;
import com.arka.taskrpro.service.TaskService;
import com.arka.taskrpro.utils.RequestContextHolder;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl  implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AppUserService userService;

    @Autowired
    ProjectService projectService;

    @Override
    public Task createTask(TaskCreateUpdateObj taskCreateUpdateObj) {

        String tenantId = RequestContextHolder.getTenantId();

        long userId = RequestContextHolder.getUserId();
        AppUser user = userService.findUserById(userId);
        Project project = projectService.getProjectDetailsById(taskCreateUpdateObj.getProjectId());



        if(user.getRole() == Role.MEMBER){
            throw new TaskException("You do not have permission to create a task!");
        }


        List<AppUser> assignedUsers = taskCreateUpdateObj.getAssignedUserIds()==null?null:
                                        taskCreateUpdateObj.getAssignedUserIds()
                                                .stream()
                                                .map(e->userService.findUserById(e))
                                                .toList();

        Task task = Task
                .builder()
                .title(taskCreateUpdateObj.getTitle())
                .description(taskCreateUpdateObj.getDescription())
                .assignedCompleteDate(taskCreateUpdateObj.getAssignedCompleteDate())
                .createdBy(user)
                .updatedBy(user)
                .assignedUsers(assignedUsers)
                .tenantId(tenantId)
                .priority(taskCreateUpdateObj.getPriority()==null?TaskPriority.LOW:taskCreateUpdateObj.getPriority())
                .status(TaskStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .project(project)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getTasks(Long projectId, Pageable pageable) {

        if(projectId==null){
            throw new TaskException("Please select a project to fetchTasks");
        }

        Project project = projectService.getProjectDetailsById(projectId);

        String tenantId = RequestContextHolder.getTenantId();
        Long userId = RequestContextHolder.getUserId();
        AppUser user = userService.getUserById(userId);
        Role role = RequestContextHolder.getRole();


        if(role!=Role.ADMIN){
            project.getMembers()
                    .stream()
                    .filter(usr-> usr.getId().equals(userId))
                    .findFirst()
                    .orElseThrow(
                            ()->new TaskException("You don't gave access to view these task")
                    );
        }

         switch (role){
             case ADMIN : return taskRepository.findAllTasksByProjectId(projectId,tenantId,pageable);
             case MANAGER:
                if(project.getManager().getId().equals(userId)){
                     return taskRepository.findAllTasksByProjectId(projectId,tenantId,pageable);
                }else{
                    return taskRepository.findByAssigneeAndProject(user,projectId,tenantId,pageable);
                }

             case MEMBER: return taskRepository.findByAssigneeAndProject(user,projectId,tenantId,pageable);
        };

        return null;
    }
}
