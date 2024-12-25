package com.swproj.SWProject.roommangement.service;

import com.swproj.SWProject.roommangement.dto.req.college.CreateCollegeReqDTO;
import com.swproj.SWProject.roommangement.dto.req.college.EditCollegeReqDTO;
import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeResDTO;

import java.util.List;

public interface CollegeService {
    void createCollege(CreateCollegeReqDTO createCollegeReqDTO);

    List<GetCollegeResDTO> getColleges();

    void removeCollege(long id);

    void editCollege(EditCollegeReqDTO editCollegeReqDTO);
    GetCollegeResDTO getCollegeById(long id);
}
