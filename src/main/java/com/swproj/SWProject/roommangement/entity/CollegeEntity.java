package com.swproj.SWProject.roommangement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.swproj.SWProject.config.entity.Users;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class CollegeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setFloors(List<FloorEntity> floors) {
        this.floors = floors;
    }

    public void setAdmins(List<Users> admins) {
        this.admins = admins;
    }

    private String name;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<FloorEntity> floors;

    @OneToMany
    private List<Users> admins;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<FloorEntity> getFloors() {
        return floors;
    }

    public List<Users> getAdmins() {
        return admins;
    }
}
