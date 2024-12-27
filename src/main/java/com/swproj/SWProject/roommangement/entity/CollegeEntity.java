package com.swproj.SWProject.roommangement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.swproj.SWProject.config.entity.Users;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class CollegeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<FloorEntity> floors;

    @OneToMany
    private List<Users> admins;

}
