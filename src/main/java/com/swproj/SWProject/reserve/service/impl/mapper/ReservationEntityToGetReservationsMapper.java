package com.swproj.SWProject.reserve.service.impl.mapper;

import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReservationEntityToGetReservationsMapper implements Function<ReserveEntity, GetReservationsResDTO> {
    @Override
    public GetReservationsResDTO apply(ReserveEntity reserveEntity) {
        return GetReservationsResDTO.builder()
                .startDate(reserveEntity.getStartDate())
                .endDate(reserveEntity.getEndDate())
                .roomId(reserveEntity.getRoomEntity().getId())
                .status(reserveEntity.getStatus())
                .startTime(reserveEntity.getStartTime())
                .endTime(reserveEntity.getEndTime())
                .peroidcally(reserveEntity.getPeroidcally())
                .build();
    }
}
