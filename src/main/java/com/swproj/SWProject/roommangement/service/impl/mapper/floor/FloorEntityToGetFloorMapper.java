package com.swproj.SWProject.roommangement.service.impl.mapper.floor;

import com.swproj.SWProject.roommangement.dto.req.room.GetRoomResDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import com.swproj.SWProject.roommangement.entity.FloorEntity;
import com.swproj.SWProject.roommangement.service.impl.mapper.room.RoomEntityToGetAllResMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FloorEntityToGetFloorMapper implements Function<FloorEntity, GetFloorResDTO> {
    private final RoomEntityToGetAllResMapper roomEntityToGetAllResMapper;

    @Override
    public GetFloorResDTO apply(FloorEntity floorEntity) {
        System.out.println(floorEntity.toString());
        List<GetRoomResDTO> roomResDTOS = (floorEntity.getRooms() != null) ?
                floorEntity.getRooms().stream()
                .map(roomEntityToGetAllResMapper)
                .toList()
                : List.of();

        return GetFloorResDTO.builder()
                .floorId(floorEntity.getId())
                .floorName(floorEntity.getFloorName())
                .floorNumber(floorEntity.getFloorNumber())
                .roomResDTOS(roomResDTOS)
                .build();
    }
}
