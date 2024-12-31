package com.swproj.SWProject.acceptreservation;

import com.swproj.SWProject.acceptreservation.test.GetReservationsAcceptResDTO;
import com.swproj.SWProject.acceptreservation.test.ReservationEntityToGetReservationsAcceptanceMapper;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.projenums.Status;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.reserve.mail.EmailService;
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
    private final EmailService emailService;
    private final UserRepo userRepo;


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

        reserveRepo.denyOtherReservations(
                reservation.getRoomEntity(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getPeriodically()
        );

        Users user = userRepo.findById(Math.toIntExact(reservation.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String message = String.format(
                "Dear %s,\n\nYour reservation for room '%s' from %s %s to %s %s has been accepted.",
                user.getUsername(),
                reservation.getRoomEntity().getRoomName(),
                reservation.getStartDate(),
                reservation.getStartTime(),
                reservation.getEndDate(),
                reservation.getEndTime()
        );

        emailService.sendSimpleMessage(
                user.getUsername(),
                "Reservation Accepted",
                message
        );

        reserveRepo.save(reservation);
    }

    @Transactional
    public void declineReservation(Long reservationId) {
        ReserveEntity reservation = reserveRepo.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() == Status.Declined) {
            throw new IllegalArgumentException("Reservation is already declined");
        }

        reservation.setStatus(Status.Declined);

        Users user = userRepo.findById(Math.toIntExact(reservation.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String message = String.format(
                "Dear %s,\n\nWe regret to inform you that your reservation for room '%s' from %s %s to %s %s has been declined.",
                user.getUsername(),
                reservation.getRoomEntity().getRoomName(),
                reservation.getStartDate(),
                reservation.getStartTime(),
                reservation.getEndDate(),
                reservation.getEndTime()
        );

        emailService.sendSimpleMessage(
                user.getUsername(),
                "Reservation Declined",
                message
        );

        reserveRepo.save(reservation);
    }
}
