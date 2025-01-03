package com.swproj.SWProject.roommangement.controller;

import com.swproj.SWProject.roommangement.dto.req.room.CreateRoomReqDTO;
import com.swproj.SWProject.roommangement.dto.req.room.EditRoomReqDTO;
import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;
import com.swproj.SWProject.roommangement.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/RoomManagement")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/createRoom")
    public void createRoom(@RequestBody CreateRoomReqDTO createRoomReqDTO) {
        roomService.createRoom(createRoomReqDTO);
    }

    @DeleteMapping("/removeRoom")
    public void removeRoom(@RequestParam long id) {
        roomService.removeRoom(id);
    }

    @PutMapping("/editRoom")
    public void editRoom(@RequestBody EditRoomReqDTO editRoomReqDTO) {
        roomService.editRoom(editRoomReqDTO);
    }

   @GetMapping("/getRooms")
    public ResponseEntity<List<GetRoomResDTO>> getRooms(@RequestParam Long floorId) {
        return ResponseEntity.ok().body(roomService.getRooms(floorId));
    }

    @GetMapping("/getRoomById")
    public ResponseEntity<GetRoomResDTO> getRoomById(@RequestParam long id) {
        return ResponseEntity.ok().body(roomService.getRoomById(id));
    }
}
