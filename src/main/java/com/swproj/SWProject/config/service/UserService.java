package com.swproj.SWProject.config.service;

import com.swproj.SWProject.config.dto.CreateUserReqDTO;
import com.swproj.SWProject.config.dto.LogInReqDTO;
import com.swproj.SWProject.config.entity.Users;

import java.util.List;

public interface UserService {
    void registerUser(CreateUserReqDTO users) throws Exception;
    List<Users> getAllUsers();

    String verify(LogInReqDTO logInReqDTO);
}
