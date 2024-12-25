package com.swproj.SWProject.roommangement.controller;

import com.swproj.SWProject.roommangement.dto.req.floor.CreateFloorReqDTO;
import com.swproj.SWProject.roommangement.dto.req.floor.EditFloorReqDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import com.swproj.SWProject.roommangement.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/RoomManagement/floor")
public class FloorController {
    private final FloorService floorService;

    @PostMapping("/create")
    public void createFloor(@RequestBody CreateFloorReqDTO createFloorReqDTO) {
        floorService.createFloor(createFloorReqDTO);
    }

    @DeleteMapping("/remove")
    public void removeFloor(@RequestParam long id) {
        floorService.removeFloor(id);
    }

    @PutMapping("/edit")
    public void editFloor(@RequestBody EditFloorReqDTO editFloorReqDTO) {
        floorService.editFloor(editFloorReqDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GetFloorResDTO>> getFloors() {
        return ResponseEntity.ok(floorService.getFloors());
    }

    @GetMapping("/getById")
    public ResponseEntity<GetFloorResDTO> getFloorById(@RequestParam long id) {
        return ResponseEntity.ok(floorService.getFloorById(id));
    }
}
