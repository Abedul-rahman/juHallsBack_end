package com.swproj.SWProject.roommangement.service;

import com.swproj.SWProject.roommangement.dto.req.floor.CreateFloorReqDTO;
import com.swproj.SWProject.roommangement.dto.req.floor.EditFloorReqDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;

import java.util.List;

public interface FloorService {
    void createFloor(CreateFloorReqDTO createFloorReqDTO);

    List<GetFloorResDTO> getFloors();

    void removeFloor(long id);

    void editFloor(EditFloorReqDTO editFloorReqDTO);

    GetFloorResDTO getFloorById(long id);
}
