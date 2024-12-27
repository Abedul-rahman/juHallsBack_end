package com.swproj.SWProject.reserve.controller;

import com.swproj.SWProject.reserve.dto.CreateReservationReqDTO;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping("/createReservation")
    public void createReservation(@RequestBody CreateReservationReqDTO createReservationReqDTO){
        reserveService.reserve(createReservationReqDTO);
    }
    @GetMapping("/getReservations")
    public List<GetReservationsResDTO> getReservations(){
        return reserveService.getReservations();
    }
}
