package com.swproj.SWProject.acceptreservation;

import com.swproj.SWProject.acceptreservation.test.GetReservationsAcceptResDTO;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ManageReservations")
@RequiredArgsConstructor
public class AcceptReservationController {
    private final AcceptReservationService acceptReservationService;

    @GetMapping("/GetPendingReservations")
    public ResponseEntity<List<GetReservationsAcceptResDTO>> getPendingReservations() {
        return ResponseEntity.ok().body(acceptReservationService.getPendingReservations());
    }

    @PostMapping("/AcceptReservation")
    public void acceptReservation(@RequestParam Long reservationId) {
        acceptReservationService.acceptReservation(reservationId);
    }

    @PostMapping("/DeclineReservation")
    public void declineReservation(@RequestParam Long reservationId) {
        acceptReservationService.declineReservation(reservationId);
    }
}
