package com.swproj.SWProject.roommangement.dto.res.floor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GetFloorResDTO {
    private Long floorId;
    private String floorName;
    private int floorNumber;
}
