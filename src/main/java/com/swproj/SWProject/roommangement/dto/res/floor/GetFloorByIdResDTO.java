package com.swproj.SWProject.roommangement.dto.res.floor;

import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetFloorByIdResDTO {
    private Long floorId;
    private String floorName;
    private int floorNumber;
    private List<GetRoomResDTO> getRoomResDTO;
}
