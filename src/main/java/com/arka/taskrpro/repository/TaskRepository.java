package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
