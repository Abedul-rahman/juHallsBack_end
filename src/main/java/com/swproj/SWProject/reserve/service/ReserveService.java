package com.swproj.SWProject.reserve.service;

import com.swproj.SWProject.reserve.dto.CreateReservationReqDTO;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;

import java.util.List;

public interface ReserveService {
    void reserve(CreateReservationReqDTO createReservationReqDTO);

    List<GetReservationsResDTO> getReservations();

}
