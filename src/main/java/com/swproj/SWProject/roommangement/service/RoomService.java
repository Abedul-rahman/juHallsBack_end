package com.swproj.SWProject.roommangement.service;

import com.swproj.SWProject.roommangement.dto.req.room.CreateRoomReqDTO;
import com.swproj.SWProject.roommangement.dto.req.room.EditRoomReqDTO;
import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;

import java.util.List;

public interface RoomService {
    void createRoom(CreateRoomReqDTO createRoomReqDTO);

    List<GetRoomResDTO> getRooms(Long floorId);

    void removeRoom(long id);

    void editRoom(EditRoomReqDTO editRoomReqDTO);

    GetRoomResDTO getRoomById(long id);
}
