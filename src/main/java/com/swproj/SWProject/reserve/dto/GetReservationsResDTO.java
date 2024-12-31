package com.swproj.SWProject.reserve.dto;

import com.swproj.SWProject.projenums.Periodically;
import com.swproj.SWProject.projenums.Status;
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
    private Periodically periodically;
    private Status status;
    private Long roomId;
}
