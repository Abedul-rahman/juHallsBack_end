package com.swproj.SWProject.acceptreservation;

import com.swproj.SWProject.acceptreservation.test.GetReservationsAcceptResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ManageReservations")
@RequiredArgsConstructor
public class ManageReservationController {
    private final ManageReservationService manageReservationService;

    @GetMapping("/GetPendingReservations")
    public ResponseEntity<List<GetReservationsAcceptResDTO>> getPendingReservations() {
        return ResponseEntity.ok().body(manageReservationService.getPendingReservations());
    }

    @PostMapping("/AcceptReservation")
    public void acceptReservation(@RequestParam Long reservationId) {
        manageReservationService.acceptReservation(reservationId);
    }

    @PostMapping("/DeclineReservation")
    public void declineReservation(@RequestParam Long reservationId) {
        manageReservationService.declineReservation(reservationId);
    }
}
