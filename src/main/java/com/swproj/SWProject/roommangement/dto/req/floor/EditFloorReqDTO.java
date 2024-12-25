package com.swproj.SWProject.roommangement.dto.req.floor;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EditFloorReqDTO {
    private long id;
    private Long collegeId;
    private int floorNumber;
    private String floorName;
    private int numberOfRooms;

}
