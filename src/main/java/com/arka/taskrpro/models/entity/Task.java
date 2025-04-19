package com.arka.taskrpro.models.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tasks")
@Builder
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String title;

    @Column
    String description;

    @Enumerated(EnumType.STRING)
    TaskStatus status;

    @Column
    LocalDateTime assignedCompleteDate;

    @Column
    LocalDateTime actualCompleteDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_user_assignments",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<AppUser> assignedUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private AppUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private AppUser updatedBy;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

}
