package com.mariaclara.spring_security.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_roles")
public class UserRole implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    private String name;

    public UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

    public long getRoleId() {
        return roleId;
    }
    public String getName() {
        return name;
    }

    public enum Values {
        ADMIN(1L),
        BASIC(2);

        long roleId;

        Values(long id) {
            this.roleId = id;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}
