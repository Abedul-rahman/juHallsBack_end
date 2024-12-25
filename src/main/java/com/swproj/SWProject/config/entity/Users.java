package com.swproj.SWProject.config.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
    @Id
    private Long id;
    private String fullName;
    private String password;
    private String role;
    private String username;

}
