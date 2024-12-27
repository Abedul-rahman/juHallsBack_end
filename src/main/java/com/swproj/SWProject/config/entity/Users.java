package com.swproj.SWProject.config.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String password;
    private String role;
    private String username;

    @ElementCollection
    @CollectionTable(name = "user_college_ids", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "college_id")
    private List<Long> collegeId;
}
