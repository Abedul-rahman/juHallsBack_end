package com.swproj.SWProject.roommangement.dto.req.college;

import lombok.Data;

import java.util.List;

@Data
public class EditCollegeReqDTO {
    private Long collegeId;
    private String collegeName;
    private List<Long> adminsId;
    private List<Long> floorsId;
}

