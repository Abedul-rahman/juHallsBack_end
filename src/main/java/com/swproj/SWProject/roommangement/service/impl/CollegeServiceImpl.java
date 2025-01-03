package com.swproj.SWProject.roommangement.service.impl;

import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.roommangement.dto.req.college.CreateCollegeReqDTO;
import com.swproj.SWProject.roommangement.dto.req.college.EditCollegeReqDTO;
import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeByIdResDTO;
import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeResDTO;
import com.swproj.SWProject.roommangement.entity.CollegeEntity;
import com.swproj.SWProject.roommangement.entity.FloorEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.CollegeRepo;
import com.swproj.SWProject.roommangement.repo.FloorRepo;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import com.swproj.SWProject.roommangement.service.CollegeService;
import com.swproj.SWProject.roommangement.service.impl.mapper.college.CollegeEntityToGetAllResMapper;
import com.swproj.SWProject.roommangement.service.impl.mapper.college.CollegeEntityToGetByIdResMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {
    private final CollegeRepo collegeRepo;
    private final CollegeEntityToGetAllResMapper collegeEntityToGetAllResMapper;
    private final CollegeEntityToGetByIdResMapper collegeEntityToGetByIdResMapper;
    private final UserRepo userRepo;
    private final FloorRepo floorRepo;
    private final RoomRepo roomRepo;

    @Override
    public void createCollege(CreateCollegeReqDTO createCollegeReqDTO) {
        CollegeEntity collegeEntity = new CollegeEntity();
        collegeEntity.setName(createCollegeReqDTO.getCollegeName());
        collegeRepo.save(collegeEntity);
    }

    @Override
    public void removeCollege(long id) {
        var collegeOpt = collegeRepo.findById(id);
        if (collegeOpt.isPresent()) {
            List<Long> floorsId = collegeOpt.get().getFloors().stream()
                    .map(FloorEntity::getId)
                    .toList();
            for (Long floorId : floorsId) {
                var floorOpt = floorRepo.findById(floorId);
                if (floorOpt.isPresent()) {
                    List<Long> roomsId = floorOpt.get().getRooms().stream()
                            .map(RoomEntity::getId)
                            .toList();
                    for (Long roomId : roomsId) {
                        roomRepo.deleteById(roomId);
                    }
                    floorRepo.deleteById(floorId);
                }
            }
            collegeRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("College with ID " + id + " not found");
        }
    }

    @Override
    public void editCollege(EditCollegeReqDTO editCollegeReqDTO) {
        CollegeEntity collegeEntity = collegeRepo.findById(editCollegeReqDTO.getCollegeId())
                .orElseThrow(() -> new IllegalArgumentException("College not found with ID: " + editCollegeReqDTO.getCollegeId()));

        collegeEntity.setName(editCollegeReqDTO.getCollegeName());

        if (editCollegeReqDTO.getAdminsId() != null && !editCollegeReqDTO.getAdminsId().isEmpty()) {
            List<Users> admins = editCollegeReqDTO.getAdminsId().stream()
                    .map(adminId -> userRepo.findById(Math.toIntExact(adminId))
                            .orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + adminId)))
                    .collect(Collectors.toList());
            collegeEntity.setAdmins(admins);
        }
        if (editCollegeReqDTO.getFloorsId() != null && !editCollegeReqDTO.getFloorsId().isEmpty()) {
            List<FloorEntity> floors = editCollegeReqDTO.getFloorsId().stream()
                    .map(floorId -> floorRepo.findById(floorId)
                            .orElseThrow(() -> new IllegalArgumentException("Floor not found with ID: " + floorId)))
                    .collect(Collectors.toList());
            collegeEntity.setFloors(floors);
        }


        collegeRepo.save(collegeEntity);
    }

    @Override
    public GetCollegeByIdResDTO getCollegeById(long id) {
        return collegeEntityToGetByIdResMapper.apply(collegeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("College not found with ID: " + id)));
    }

    @Override
    public List<GetCollegeResDTO> getColleges() {
        return collegeRepo.findAll()
                .stream()
                .map(collegeEntityToGetAllResMapper)
                .toList();
    }
}
