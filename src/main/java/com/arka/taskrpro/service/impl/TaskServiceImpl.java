package com.arka.taskrpro.service.impl;


import com.arka.taskrpro.exceptions.TaskException;
import com.arka.taskrpro.models.domain.TaskCreateUpdateObj;
import com.arka.taskrpro.models.dto.TaskDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.models.entity.Task;
import com.arka.taskrpro.models.entity.TaskStatus;
import com.arka.taskrpro.repository.TaskRepository;
import com.arka.taskrpro.service.AppUserService;
import com.arka.taskrpro.service.TaskService;
import com.arka.taskrpro.utils.RequestContextHolder;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl  implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AppUserService userService;

    @Override
    public Task createTask(TaskCreateUpdateObj taskCreateUpdateObj) {

        String tenantId = RequestContextHolder.getTenantId();

        long userId = RequestContextHolder.getUserId();
        AppUser user = userService.findUserById(userId);

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
                .status(TaskStatus.IN_PROGRESS)
                .build();

        return taskRepository.save(task);
    }
}
