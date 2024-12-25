package com.swproj.SWProject.roommangement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
public class FloorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private int numberOfRooms;
    private int floorNumber;
    private String floorName;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomEntity> rooms;



    @ManyToOne
    @JoinColumn(name = "college_id", nullable = false)
    @JsonBackReference
    private CollegeEntity college;

    public Long getId() {
        return id;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getFloorName() {
        return floorName;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public CollegeEntity getCollege() {
        return college;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public void setCollege(CollegeEntity college) {
        this.college = college;
    }

}

