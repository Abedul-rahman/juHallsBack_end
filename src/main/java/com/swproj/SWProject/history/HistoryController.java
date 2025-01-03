package com.swproj.SWProject.history;


import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/History")
public class HistoryController {
    private final HistoryServiceImpl historyService;

    @GetMapping("/GetHistory")
    public List<GetReservationsResDTO> getHistory(@RequestParam String username) {
        return historyService.getHistory(username);
    }

    @PostMapping("/CancelReservation")
    public void cancelReservation(@RequestParam Long reservationId) {
        historyService.cancelReservation(reservationId);
    }
}
