package com.swproj.SWProject.main;

import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/main")
public class MainController {
    private final MainServiceImpl mainService;


    //TODO:SHOW CURRENT  EVENTS (max 4)
    //todo allow user to cancel res
    @GetMapping("/getMainReservations")
    public List<GetReservationsResDTO> getMain(){
        return mainService.getMainReservations();
    }
}
