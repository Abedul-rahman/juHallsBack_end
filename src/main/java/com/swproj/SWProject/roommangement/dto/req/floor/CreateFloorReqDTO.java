package com.swproj.SWProject.roommangement.dto.req.floor;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateFloorReqDTO {
    private int floorNumber;
    private String floorName;
    private Long collegeId;
    private int numberOfRooms;

}
