package com.arka.taskrpro.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tenant_id", unique = true, nullable = false)
    String tenant_id;

    @Column(name = "name", unique = true, nullable = false)
    String name;

    @OneToOne
    @JoinColumn(name = "owner_user_id", unique = true, nullable = false)
    AppUser owner;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
