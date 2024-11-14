package com.mariaclara.spring_security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariaclara.spring_security.entities.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    
}
