package com.swproj.SWProject.roommangement.dto.req.room;

import com.swproj.SWProject.roommangement.enums.ROOM_TYPE;
import lombok.Data;

@Data
public class CreateRoomReqDTO {
    private Long floorId;
    private String roomName;
    private int floorNumber;
    private int numberOfSeats;
    private ROOM_TYPE roomType;
}
