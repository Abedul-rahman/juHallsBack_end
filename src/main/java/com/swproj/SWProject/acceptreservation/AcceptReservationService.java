package com.swproj.SWProject.acceptreservation;

import com.swproj.SWProject.acceptreservation.test.GetReservationsAcceptResDTO;
import com.swproj.SWProject.acceptreservation.test.ReservationEntityToGetReservationsAcceptanceMapper;
import com.swproj.SWProject.projenums.Status;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import com.swproj.SWProject.reserve.service.impl.mapper.ReservationEntityToGetReservationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcceptReservationService {
    private final ReserveRepo reserveRepo;
    private final ReservationEntityToGetReservationsAcceptanceMapper mapper;

    public List<GetReservationsAcceptResDTO> getPendingReservations() {
        return reserveRepo.findByStatus(Status.Pending).stream()
                .map(mapper).toList();
    }

    public void acceptReservation(Long reservationId) {
        ReserveEntity reservation = reserveRepo.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() == Status.Accepted) {
            throw new IllegalArgumentException("Reservation is already accepted");
        }

        reservation.setStatus(Status.Accepted);
        reserveRepo.save(reservation);
    }
}
