package com.swproj.SWProject.reserve.service.impl;

import com.swproj.SWProject.reserve.dto.CreateReservationReqDTO;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import com.swproj.SWProject.reserve.service.ReserveService;
import com.swproj.SWProject.reserve.service.impl.mapper.CreateReservationToEntityMapper;
import com.swproj.SWProject.reserve.service.impl.mapper.ReservationEntityToGetReservationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReserveServiceImpl implements ReserveService {
    private final CreateReservationToEntityMapper createReservationToEntityMapper;
    private final ReserveRepo reserveRepo;
    private final ReservationEntityToGetReservationsMapper reservationEntityToGetReservationsMapper;
    @Override
    public void reserve(CreateReservationReqDTO createReservationReqDTO) {
        reserveRepo.save(createReservationToEntityMapper.apply(createReservationReqDTO));
    }

    @Override
    public List<GetReservationsResDTO> getReservations() {
        return reserveRepo.findAll().stream()
                .map(reservationEntityToGetReservationsMapper).toList();
    }

}
