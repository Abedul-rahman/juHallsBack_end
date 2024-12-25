package com.swproj.SWProject.roommangement.dto.res.floor;

import com.swproj.SWProject.roommangement.dto.req.room.GetRoomResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@Builder
public class GetFloorResDTO {
    Long floorId;
    String floorName;
    int floorNumber;
    List<GetRoomResDTO> roomResDTOS;
}
