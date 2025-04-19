package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
