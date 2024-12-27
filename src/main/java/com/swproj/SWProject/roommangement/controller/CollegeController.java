package com.swproj.SWProject.roommangement.controller;

import com.swproj.SWProject.roommangement.dto.req.college.CreateCollegeReqDTO;
import com.swproj.SWProject.roommangement.dto.req.college.EditCollegeReqDTO;
import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeByIdResDTO;
import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeResDTO;
import com.swproj.SWProject.roommangement.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/RoomManagement/college")
public class CollegeController {
    private final CollegeService collegeService;

    @PostMapping("/createCollege")
    public void createCollege(@RequestBody CreateCollegeReqDTO createCollegeReqDTO) {
        collegeService.createCollege(createCollegeReqDTO);
    }

    @DeleteMapping("/removeCollege")
    public void removeCollege(@RequestParam long id){
        collegeService.removeCollege(id);
    }

    @PutMapping("/editCollege")
    public void editCollege(@RequestBody EditCollegeReqDTO editCollegeReqDTO){
        collegeService.editCollege(editCollegeReqDTO);
    }
    @GetMapping("/getColleges")
    public ResponseEntity<List<GetCollegeResDTO>> getColleges() {
        return ResponseEntity.ok().body(collegeService.getColleges());
    }
    @GetMapping("/getCollegeById")
    public ResponseEntity<GetCollegeByIdResDTO> getCollegeById(@RequestParam long id){
        return ResponseEntity.ok().body(collegeService.getCollegeById(id));
    }
}
