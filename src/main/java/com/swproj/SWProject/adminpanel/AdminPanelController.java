package com.swproj.SWProject.adminpanel;

import com.swproj.SWProject.config.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/AdminPanel")
@RestController
@RequiredArgsConstructor
public class AdminPanelController {

    private final AdminPanelService adminPanelService;

    @GetMapping("/getListOfUsers")
    public Page<Users> getUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return adminPanelService.getUsers(keyword, page, size);
    }

    @PostMapping("/setUserRole")
    public String setUserRole(
            @RequestParam Long userId,
            @RequestParam(required = false) Long collegeId ,
            @RequestParam String role
    ) {
        return adminPanelService.setUserRole(userId, collegeId, role);
    }
}
