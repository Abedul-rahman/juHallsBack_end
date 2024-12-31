package com.swproj.SWProject.roommangement.service.impl.mapper.floor;

import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorByIdResDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import com.swproj.SWProject.roommangement.dto.res.room.GetRoomResDTO;
import com.swproj.SWProject.roommangement.entity.FloorEntity;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import com.swproj.SWProject.roommangement.service.impl.mapper.room.RoomEntityToGetAllResMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FloorEntityToGetAllFloorMapper implements Function<FloorEntity, GetFloorResDTO> {

    @Override
    public GetFloorResDTO apply(FloorEntity floorEntity) {
        return GetFloorResDTO.builder()
                .floorId(floorEntity.getId())
                .floorName(floorEntity.getFloorName())
                .floorNumber(floorEntity.getFloorNumber())
                .build();
    }

}
