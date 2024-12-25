package com.swproj.SWProject.roommangement.dto.req.room;

import lombok.Data;

@Data
public class EditRoomReqDTO {
    private Long id;
    private String roomName;
    private int numberOfSeats;
    private int floorNumber;
}