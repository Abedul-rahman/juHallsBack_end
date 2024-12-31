package com.swproj.SWProject.roommangement.service.impl.mapper.college;

import com.swproj.SWProject.config.dto.UsersDTO;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeByIdResDTO;
import com.swproj.SWProject.roommangement.dto.res.floor.GetFloorResDTO;
import com.swproj.SWProject.roommangement.entity.CollegeEntity;
import com.swproj.SWProject.roommangement.service.impl.mapper.floor.FloorEntityToGetAllFloorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CollegeEntityToGetByIdResMapper implements Function<CollegeEntity, GetCollegeByIdResDTO> {
    private final UserRepo userRepo;
    private final FloorEntityToGetAllFloorMapper floorEntityToGetAllFloorMapper;

    @Override
    public GetCollegeByIdResDTO apply(CollegeEntity collegeEntity) {
        List<Users> users = userRepo.findByCollegeId(collegeEntity.getId());
        List<UsersDTO> admins = users.stream()
                .map(user -> UsersDTO.builder()
                        .username(user.getUsername())
                        .build())
                .collect(Collectors.toList());

        List<GetFloorResDTO> floors = collegeEntity.getFloors().stream()
                .map(floorEntityToGetAllFloorMapper)
                .toList();

        return GetCollegeByIdResDTO.builder()
                .id(collegeEntity.getId())
                .collegeName(collegeEntity.getName())
                .admins(admins)
                .getFloorResDTO(floors)
                .build();
    }
}
