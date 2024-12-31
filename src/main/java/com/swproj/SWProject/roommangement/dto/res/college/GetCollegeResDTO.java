package com.swproj.SWProject.roommangement.dto.res.college;

import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCollegeResDTO {
    private Long id;
    private String collegeName;
}
