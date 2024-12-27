package com.swproj.SWProject.roommangement.dto.res.college;

import com.swproj.SWProject.config.dto.UsersDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetCollegeByIdResDTO {
    private Long id;
    private String collegeName;
    private List<UsersDTO> admins;
    private List<GetFloorResDTO> getFloorResDTO;
}
