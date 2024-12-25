package com.swproj.SWProject.config.service;

import com.swproj.SWProject.config.dto.LogInReqDTO;
import com.swproj.SWProject.config.entity.Users;

import java.util.List;

public interface UserService {
    Users registerUser(Users users);
    List<Users> getAllUsers();

    String verify(LogInReqDTO logInReqDTO);
}
