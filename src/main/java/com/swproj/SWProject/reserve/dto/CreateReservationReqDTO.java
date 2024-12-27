package com.swproj.SWProject.reserve.dto;

import com.swproj.SWProject.projenums.Periodically;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateReservationReqDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Periodically periodically;
    private Long roomId;
    private String username;
}
