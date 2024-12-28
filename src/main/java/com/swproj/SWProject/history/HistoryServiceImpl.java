package com.swproj.SWProject.history;

import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import com.swproj.SWProject.reserve.service.impl.mapper.ReservationEntityToGetReservationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl {
    private final ReserveRepo reserveRepo;
    private final ReservationEntityToGetReservationsMapper reservationEntityToGetReservationsMapper;

    public List<GetReservationsResDTO> getHistory(Long userId){
        List<ReserveEntity> reservations = reserveRepo.getReserveEntityByUserId(userId);

        return reservations.stream().map(reservationEntityToGetReservationsMapper).toList();
    }
}
