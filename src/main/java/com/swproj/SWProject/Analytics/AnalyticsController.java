package com.swproj.SWProject.Analytics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/total-reservations")
    public long getTotalReservations() {
        return analyticsService.getTotalReservations();
    }

    @GetMapping("/active-reservations")
    public long getActiveReservations() {
        return analyticsService.getActiveReservations();
    }

    @GetMapping("/utilization-rate")
    public double getRoomUtilizationRate() {
        return analyticsService.getRoomUtilizationRate();
    }

    @GetMapping("/top-rooms")
    public Map<String, Long> getTopReservedRooms(@RequestParam(defaultValue = "5") int limit) {
        return analyticsService.getTopReservedRooms(limit);
    }

    @GetMapping("/reservations-over-time")
    public Map<LocalDate, Long> getReservationsOverTime(
            @RequestBody ReqDTO reqDTO) {
        return analyticsService.getReservationsOverTime(reqDTO);
    }
}
