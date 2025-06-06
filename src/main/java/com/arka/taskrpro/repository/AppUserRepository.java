package com.arka.taskrpro.repository;

import com.arka.taskrpro.models.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long>, JpaSpecificationExecutor<AppUser> {
    public Optional<AppUser> findUserByEmail(String email);
}
