package com.swproj.SWProject.reserve.service.impl.mapper;

import com.swproj.SWProject.Status;
import com.swproj.SWProject.reserve.dto.CreateReservationReqDTO;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CreateReservationToEntityMapper implements Function<CreateReservationReqDTO, ReserveEntity> {
    private final RoomRepo roomRepo;
    @Override
    public ReserveEntity apply(CreateReservationReqDTO createReservationReqDTO) {
        RoomEntity roomEntity = roomRepo.findById(createReservationReqDTO.getRoomId()).orElse(null);
        return ReserveEntity.builder()
                .startDate(createReservationReqDTO.getStartDate())
                .endDate(createReservationReqDTO.getEndDate())
                .peroidcally(createReservationReqDTO.getPeroidcally())
                .endTime(createReservationReqDTO.getEndTime())
                .status(Status.Pending)
                .startTime(createReservationReqDTO.getStartTime())
                .roomEntity(roomEntity)
                .build();
    }
}
