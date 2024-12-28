package com.swproj.SWProject.Analytics;

import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final ReserveRepo reserveRepo;

    public long getTotalReservations() {
        return reserveRepo.count();
    }

    public long getActiveReservations() {
        return reserveRepo.findAll()
                .stream()
                .filter(reserve -> reserve.getStartDate().isBefore(LocalDate.now()) &&
                        reserve.getEndDate().isAfter(LocalDate.now()))
                .count();
    }

    public double getRoomUtilizationRate() {
        List<ReserveEntity> reservations = reserveRepo.findAll();
        long totalDays = reservations.stream()
                .mapToLong(reservation -> ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate()))
                .sum();
        long totalRooms = reservations.size();
        return totalRooms == 0 ? 0 : ((double) totalDays / (totalRooms * 365)) * 100; // Assuming 365 days/year.
    }

    public Map<String, Long> getTopReservedRooms(int limit) {
        return reserveRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        reserve -> reserve.getRoomEntity().getRoomName(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<LocalDate, Long> getReservationsOverTime(ReqDTO reqDTO) {
        return reserveRepo.findAll()
                .stream()
                .filter(reserve -> !reserve.getStartDate().isBefore(reqDTO.getStartDate()) &&
                        !reserve.getEndDate().isAfter(reqDTO.getEndDate()))
                .collect(Collectors.groupingBy(ReserveEntity::getStartDate, Collectors.counting()));
    }
}
