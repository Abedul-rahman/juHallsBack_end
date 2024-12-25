package com.swproj.SWProject.roommangement.service.impl.mapper.room;

import com.swproj.SWProject.roommangement.dto.req.room.CreateRoomReqDTO;
import com.swproj.SWProject.roommangement.entity.FloorEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.FloorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CreateRoomReqDTOToEntityMapper implements Function<CreateRoomReqDTO, RoomEntity> {
    private final FloorRepo floorRepo;
    @Override
    public RoomEntity apply(CreateRoomReqDTO createRoomReqDTO) {
        FloorEntity floor = floorRepo.findById(createRoomReqDTO.getFloorId()).orElseThrow(null);
        return RoomEntity.builder()
                .roomName(createRoomReqDTO.getRoomName())
                .floorNumber(createRoomReqDTO.getFloorNumber())
                .numberOfSeats(createRoomReqDTO.getNumberOfSeats())
                .floor(floor)
                .roomType(createRoomReqDTO.getRoomType())
                .build();
    }
}
