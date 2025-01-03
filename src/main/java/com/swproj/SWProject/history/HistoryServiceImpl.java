package com.swproj.SWProject.history;

import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.projenums.Status;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.reserve.mail.EmailService;
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
    private final UserRepo userRepo;
    private final EmailService emailService;

    public List<GetReservationsResDTO> getHistory(String username){
        Users user = userRepo.findByUsername(username);
        List<ReserveEntity> reservations = reserveRepo.getReserveEntityByUserId(user.getId());

        return reservations.stream().map(reservationEntityToGetReservationsMapper).toList();
    }
    public void cancelReservation(Long reservationId, String username) {
        ReserveEntity reservation = reserveRepo.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() == Status.Canceled) {
            throw new IllegalArgumentException("Reservation is already canceled");
        }

        reservation.setStatus(Status.Canceled);

        reserveRepo.save(reservation);

        Users reservationUser = userRepo.findByUsername(username);
        if (reservationUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        List<Users> collegeAdmins = new ArrayList<>();
        for (Long collegeId : reservationUser.getCollegeId()) {
            collegeAdmins.addAll(userRepo.findByCollegeId(collegeId));
        }


        String message = String.format(
                "Reservation for room '%s' from %s %s to %s %s has been canceled.",
                reservation.getRoomEntity().getRoomName(),
                reservation.getStartDate(),
                reservation.getStartTime(),
                reservation.getEndDate(),
                reservation.getEndTime()
        );

        for (Users user : collegeAdmins) {
            emailService.sendSimpleMessage(
                    user.getUsername(),
                    "Reservation Canceled",
                    message
            );
        }
    }
}

