package com.swproj.SWProject.roommangement.service.impl.mapper.room;

import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoomEntityToGetAllResMapper implements Function<RoomEntity, GetRoomResDTO> {
    @Override
    public GetRoomResDTO apply(RoomEntity roomEntity) {
        return GetRoomResDTO.builder()
                .id(roomEntity.getId())
                .roomName(roomEntity.getRoomName())
                .floorNumber(roomEntity.getFloorNumber())
                .numberOfSeats(roomEntity.getNumberOfSeats())
                .roomType(roomEntity.getRoomType())
                .build();
    }
}
