package com.swproj.SWProject.main;

import com.swproj.SWProject.config.entity.UserPrincipal;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.reserve.dto.GetReservationsResDTO;
import com.swproj.SWProject.reserve.repo.ReserveRepo;
import com.swproj.SWProject.reserve.service.impl.mapper.ReservationEntityToGetReservationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainServiceImpl {
    private final ReserveRepo reserveRepo;
    private final ReservationEntityToGetReservationsMapper reservationEntityToGetReservationsMapper;
    private final UserRepo userRepo;
    public List<GetReservationsResDTO> getMainReservations() {

        if (getCurrentUserRole().toString().equalsIgnoreCase("doctor") || getCurrentUserRole().toString().equalsIgnoreCase("student")) {
            Users user= getCurrentUser();
            return reserveRepo.findLast4ByUserId(user.getId()).stream().map(reservationEntityToGetReservationsMapper).toList();
        }
        return reserveRepo.findLast4().stream().map(reservationEntityToGetReservationsMapper).toList();
    }
    private String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users users = userPrincipal.getUser();
            return users.getRole();
        }
        throw new RuntimeException("Unable to determine user role");
    }
    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users user = userPrincipal.getUser();
            return user;
        }
        throw new RuntimeException("Unable to determine user");
    }
}
