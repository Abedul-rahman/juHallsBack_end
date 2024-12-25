package com.swproj.SWProject.roommangement.dto.req.room;

import com.swproj.SWProject.roommangement.ROOM_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetRoomResDTO {
    private Long id;
    private String roomName;
    private int numberOfSeats;
    private int floorNumber;
    private ROOM_TYPE roomType;
}
