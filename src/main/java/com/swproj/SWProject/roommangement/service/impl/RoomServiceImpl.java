package com.swproj.SWProject.roommangement.service.impl;

import com.swproj.SWProject.roommangement.dto.req.room.CreateRoomReqDTO;
import com.swproj.SWProject.roommangement.dto.req.room.EditRoomReqDTO;
import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import com.swproj.SWProject.roommangement.service.RoomService;
import com.swproj.SWProject.roommangement.service.impl.mapper.room.CreateRoomReqDTOToEntityMapper;
import com.swproj.SWProject.roommangement.service.impl.mapper.room.RoomEntityToGetAllResMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final CreateRoomReqDTOToEntityMapper createRoomReqDTOToEntityMapper;
    private final RoomEntityToGetAllResMapper roomEntityToGetAllResMapper;
    @Override
    public void createRoom(CreateRoomReqDTO createRoomReqDTO) {
        roomRepo.save(createRoomReqDTOToEntityMapper.apply(createRoomReqDTO));
    }

    @Override
    public List<GetRoomResDTO> getRooms(Long floorId) {
        return roomRepo.findByFloorId(floorId).stream().map(roomEntityToGetAllResMapper).toList();
    }

    @Override
    public void removeRoom(long id) {
        roomRepo.deleteById(id);
    }

    @Override
    public void editRoom(EditRoomReqDTO editRoomReqDTO) {
        RoomEntity roomEntity = roomRepo.findById(editRoomReqDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + editRoomReqDTO.getId()));
        roomEntity.setRoomName(editRoomReqDTO.getRoomName());
        roomEntity.setNumberOfSeats(editRoomReqDTO.getNumberOfSeats());
        roomEntity.setFloorNumber(editRoomReqDTO.getFloorNumber());
        roomRepo.save(roomEntity);
    }

    @Override
    public GetRoomResDTO getRoomById(long id) {
        RoomEntity roomEntity = roomRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + id));
        return new GetRoomResDTO(
                roomEntity.getId(),
                roomEntity.getRoomName(),
                roomEntity.getNumberOfSeats(),
                roomEntity.getFloorNumber(),
                roomEntity.getRoomType()
        );
    }
}
