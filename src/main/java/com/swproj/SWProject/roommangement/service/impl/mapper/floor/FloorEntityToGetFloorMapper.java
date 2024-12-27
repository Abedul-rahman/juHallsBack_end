package com.swproj.SWProject.roommangement.service.impl.mapper.floor;

import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorByIdResDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;
import com.swproj.SWProject.roommangement.entity.FloorEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.FloorRepo;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import com.swproj.SWProject.roommangement.service.impl.mapper.room.RoomEntityToGetAllResMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FloorEntityToGetFloorMapper implements Function<FloorEntity, GetFloorByIdResDTO> {
    private final RoomRepo roomRepo;
    private final RoomEntityToGetAllResMapper roomEntityToGetAllResMapper;
    @Override
    public GetFloorByIdResDTO apply(FloorEntity floorEntity) {
        List<GetRoomResDTO> roomResDTOs = roomRepo.findByFloorId(floorEntity.getId()).stream()
                .map(roomEntityToGetAllResMapper)
                .toList();

        return GetFloorByIdResDTO.builder()
                .floorId(floorEntity.getId())
                .floorName(floorEntity.getFloorName())
                .floorNumber(floorEntity.getFloorNumber())
                .getRoomResDTO(roomResDTOs)
                .build();
    }

}
