package com.swproj.SWProject.roommangement.service.impl.mapper.college;

import com.swproj.SWProject.roommangement.dto.res.college.GetCollegeResDTO;
import com.swproj.SWProject.roommangement.entity.CollegeEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CollegeEntityToGetAllResMapper implements Function<CollegeEntity, GetCollegeResDTO> {
    @Override
    public GetCollegeResDTO apply(CollegeEntity collegeEntity) {
        if (collegeEntity.getFloors() !=null){
            //List<GetFloorResDTO> getFloorResDTOList = collegeEntity.getFloors().stream().filter().toList();
        }
        return GetCollegeResDTO
                .builder()
                .id(collegeEntity.getId())
                .collegeName(collegeEntity.getName())
                //.floors(collegeEntity.getFloors())

                .build();
    }
}
