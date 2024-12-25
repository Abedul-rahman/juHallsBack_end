package com.swproj.SWProject.config.controller;

import com.swproj.SWProject.config.dto.LogInReqDTO;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createUser")
    public Users createUser(@RequestBody Users user) {
        return userService.registerUser(user);
    }
    @GetMapping("/getUsers")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/login")
    public String login(@RequestBody LogInReqDTO logInReqDTO) {
        return userService.verify(logInReqDTO);
    }
}
