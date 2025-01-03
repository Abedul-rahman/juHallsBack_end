package com.swproj.SWProject.roommangement.service.impl;

import com.swproj.SWProject.roommangement.dto.req.floor.CreateFloorReqDTO;
import com.swproj.SWProject.roommangement.dto.req.floor.EditFloorReqDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorByIdResDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import com.swproj.SWProject.roommangement.entity.CollegeEntity;
import com.swproj.SWProject.roommangement.entity.FloorEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.CollegeRepo;
import com.swproj.SWProject.roommangement.repo.FloorRepo;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import com.swproj.SWProject.roommangement.service.FloorService;
import com.swproj.SWProject.roommangement.service.impl.mapper.floor.FloorEntityToGetAllFloorMapper;
import com.swproj.SWProject.roommangement.service.impl.mapper.floor.FloorEntityToGetFloorMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {
    private final FloorRepo floorRepo;
    private final FloorEntityToGetFloorMapper floorEntityToGetFloorMapper;
    private final FloorEntityToGetAllFloorMapper floorEntityToGetAllFloorMapper;
    private final CollegeRepo collegeRepo;
    private final RoomRepo roomRepo;
    @Override
    public void createFloor(CreateFloorReqDTO createFloorReqDTO) {
        FloorEntity floorEntity = new FloorEntity();
        CollegeEntity college = collegeRepo.findById(createFloorReqDTO.getCollegeId()).orElseThrow(null);
        floorEntity.setFloorName(createFloorReqDTO.getFloorName());
        floorEntity.setFloorNumber(createFloorReqDTO.getFloorNumber());
        floorEntity.setCollege(college);
        floorRepo.save(floorEntity);
    }

    @Override
    public List<GetFloorResDTO> getFloors(Long collegeId) {
        return floorRepo.findByCollegeId(collegeId).stream().map(floorEntityToGetAllFloorMapper).toList();
    }

    @Override
    public void removeFloor(long id) {
        var floorOpt = floorRepo.findById(id);
        if (floorOpt.isPresent()) {
            List<Long> roomsId = floorOpt.get().getRooms().stream()
                    .map(RoomEntity::getId)
                    .toList();
            for (Long roomId : roomsId) {
                roomRepo.deleteById(roomId);
            }
            floorRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Floor with ID " + id + " not found");
        }
    }

    @Override
    public void editFloor(EditFloorReqDTO editFloorReqDTO) {
        FloorEntity floorEntity = floorRepo.findById(editFloorReqDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Floor not found with ID: " + editFloorReqDTO.getId()));
        floorEntity.setFloorName(editFloorReqDTO.getFloorName());
        floorEntity.setFloorNumber(editFloorReqDTO.getFloorNumber());
        floorEntity.setNumberOfRooms(editFloorReqDTO.getNumberOfRooms());
        floorRepo.save(floorEntity);
    }

    @Override
    public GetFloorByIdResDTO getFloorById(long id) {
        return floorEntityToGetFloorMapper.apply(floorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Floor not found with ID: " + id)));
        }
    }
