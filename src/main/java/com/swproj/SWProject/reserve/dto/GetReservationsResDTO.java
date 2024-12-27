package com.swproj.SWProject.reserve.dto;

import com.swproj.SWProject.Peroidcally;
import com.swproj.SWProject.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class GetReservationsResDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Peroidcally peroidcally;
    private Status status;
    private Long roomId;
}
