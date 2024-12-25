package com.swproj.SWProject.roommangement.entity;

import com.swproj.SWProject.roommangement.ROOM_TYPE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String roomName;
    private int numberOfSeats;

    private int floorNumber;
    @Enumerated(EnumType.STRING)
    private ROOM_TYPE roomType;
    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private FloorEntity floor;
}
