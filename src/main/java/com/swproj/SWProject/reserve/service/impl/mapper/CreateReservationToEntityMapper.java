package com.swproj.SWProject.reserve.service.impl.mapper;

import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.projenums.Status;
import com.swproj.SWProject.reserve.dto.CreateReservationReqDTO;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import com.swproj.SWProject.roommangement.repo.RoomRepo;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CreateReservationToEntityMapper implements Function<CreateReservationReqDTO, ReserveEntity> {
    private final RoomRepo roomRepo;
    private final ReserveRepo reserveRepo;
    private final UserRepo userRepo;

    @Override
    public ReserveEntity apply(CreateReservationReqDTO createReservationReqDTO) {
        Users user = userRepo.findByUsername(createReservationReqDTO.getUsername());
        RoomEntity roomEntity = roomRepo.findById(createReservationReqDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        boolean isConflictingReservation = reserveRepo.checkConflicts(
                roomEntity,
                Status.Accepted,
                createReservationReqDTO.getEndDate(),
                createReservationReqDTO.getStartDate(),
                createReservationReqDTO.getEndTime(),
                createReservationReqDTO.getStartTime(),
                createReservationReqDTO.getPeriodically()
        );

        if (isConflictingReservation) {
            throw new IllegalArgumentException("The room is already reserved during the requested time and period");
        }

        return ReserveEntity.builder()
                .startDate(createReservationReqDTO.getStartDate())
                .endDate(createReservationReqDTO.getEndDate())
                .periodically(createReservationReqDTO.getPeriodically())
                .endTime(createReservationReqDTO.getEndTime())
                .status(Status.Pending)
                .startTime(createReservationReqDTO.getStartTime())
                .roomEntity(roomEntity)
                .userId(user.getId())
                .build();
    }
}
