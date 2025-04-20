package com.arka.taskrpro.service.impl;

import com.arka.taskrpro.exceptions.ProjectException;
import com.arka.taskrpro.exceptions.UserException;
import com.arka.taskrpro.models.domain.AppUserSummary;
import com.arka.taskrpro.models.domain.ProjectCreateUpdate;
import com.arka.taskrpro.models.dto.AppUserSummaryDto;
import com.arka.taskrpro.models.entity.AppUser;
import com.arka.taskrpro.models.entity.Project;
import com.arka.taskrpro.models.entity.Role;
import com.arka.taskrpro.repository.AppUserRepository;
import com.arka.taskrpro.repository.ProjectRepository;
import com.arka.taskrpro.service.ProjectService;
import com.arka.taskrpro.utils.RequestContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    AppUserRepository userRepository;

    @Override
    public Project createProject(ProjectCreateUpdate project) {

        String tenantId = RequestContextHolder.getTenantId();

        Long userId = RequestContextHolder.getUserId();
        AppUser user = userRepository.findById(userId).orElseThrow(()->new UserException("Not a valid user!"));

        if(user.getRole()== Role.MEMBER){
            throw new ProjectException("User does not have permission to create a project!");
        }

        AppUser manager = userRepository.findById(project.getManager()).orElseThrow(()->new ProjectException("Please assign valid manager for project!"));

        if(manager.getRole()!=Role.MANAGER){
            throw new  ProjectException("Please assign valid manager for project!");
        }

        List<AppUser> members = null;
        if(project.getMembers()!=null && !project.getMembers().isEmpty()){
            members = project.getMembers()
                    .stream()
                    .map(id->userRepository.findById(id).orElseThrow(()->new UserException("Member with user id "+id+" is not a valid user!")))
                    .toList();
        }

        Project newProject = Project
                .builder()
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .tenantId(tenantId)
                .createdBy(user)
                .manager(manager)
                .members(members)
                .build();


        return projectRepository.save(newProject);

    }

    @Override
    public Page<Project> getProjects(Pageable pageable) {

        String tenantId = RequestContextHolder.getTenantId();
        Long userId = RequestContextHolder.getUserId();
        AppUser user = userRepository.findById(userId).orElseThrow(()->new UserException("User not found!"));
        Role role = RequestContextHolder.getRole();

        switch (role){
            case ADMIN -> {
                return projectRepository.findAllProjectsByTenant(tenantId,pageable);
            }
            case MANAGER, MEMBER -> {
                return projectRepository.findByMemberAndTenant(user,tenantId,pageable);
            }

        }

        throw new ProjectException("You don't have permission to see projects");
    }

    @Override
    public List<AppUserSummary> getUsersForProject(Long projectId) {

      try{
          Project project = getProjectDetailsById(projectId);

          if(project.getMembers()!=null){
              return project.getMembers()
                      .stream()
                      .map(user -> AppUserSummary
                              .builder()
                              .id(user.getId())
                              .name(user.getName())
                              .build()
                      )
                      .toList();
          }
      }catch (Exception e){
          throw new ProjectException("Error fetching members!");
      }


        return new ArrayList<>();
    }

    @Override
    public Project getProjectDetailsById(Long projectId) {
        return  projectRepository.findById(projectId).orElseThrow(()->new ProjectException("Not a valid project!"));
    }
}
