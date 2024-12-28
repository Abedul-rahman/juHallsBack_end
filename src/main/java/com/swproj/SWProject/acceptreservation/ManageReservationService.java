package com.swproj.SWProject.acceptreservation;

import com.swproj.SWProject.acceptreservation.test.GetReservationsAcceptResDTO;
import com.swproj.SWProject.acceptreservation.test.ReservationEntityToGetReservationsAcceptanceMapper;
import com.swproj.SWProject.projenums.Status;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageReservationService {
    private final ReserveRepo reserveRepo;
    private final ReservationEntityToGetReservationsAcceptanceMapper mapper;

    public List<GetReservationsAcceptResDTO> getPendingReservations() {
        return reserveRepo.findByStatus(Status.Pending).stream()
                .map(mapper).toList();
    }
    @Transactional
    public void acceptReservation(Long reservationId) {
        ReserveEntity reservation = reserveRepo.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() == Status.Accepted) {
            throw new IllegalArgumentException("Reservation is already accepted");
        }

        reservation.setStatus(Status.Accepted);

        reserveRepo.denyOtherReservations(reservation.getRoomEntity()
                ,reservation.getStartDate()
                ,reservation.getEndDate()
                ,reservation.getStartTime()
                ,reservation.getEndTime()
                ,reservation.getPeriodically());
        reserveRepo.save(reservation);
    }

    public void declineReservation(Long reservationId) {
        ReserveEntity reserve =reserveRepo.findById(reservationId).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reserve.setStatus(Status.Declined);
        reserveRepo.save(reserve);
    }
}
