package com.swproj.SWProject.acceptreservation.test;

import com.swproj.SWProject.reserve.entity.ReserveEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReservationEntityToGetReservationsAcceptanceMapper implements Function<ReserveEntity, GetReservationsAcceptResDTO> {
    @Override
    public GetReservationsAcceptResDTO apply(ReserveEntity reserveEntity) {

        return GetReservationsAcceptResDTO.builder()
                .userId(reserveEntity.getUserId())
                .reservationId(reserveEntity.getId())
                .startDate(reserveEntity.getStartDate())
                .endDate(reserveEntity.getEndDate())
                .roomId(reserveEntity.getRoomEntity().getId())
                .status(reserveEntity.getStatus())
                .startTime(reserveEntity.getStartTime())
                .endTime(reserveEntity.getEndTime())
                .periodically(reserveEntity.getPeriodically())
                .build();
    }
}
