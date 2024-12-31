package com.swproj.SWProject.config.service.impl;

import com.swproj.SWProject.config.dto.CreateUserReqDTO;
import com.swproj.SWProject.config.dto.LogInReqDTO;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import com.swproj.SWProject.config.service.JWT.JWTService;
import com.swproj.SWProject.config.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Override
    public void registerUser(CreateUserReqDTO user) throws Exception {
        String username = user.getUsername();
        if (userRepo.findByUsername(username) != null) {
            throw new Exception("User already registered using this account");
        }
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(encoder.encode(user.getPassword()));
        users.setFullName(user.getFullName());
        users.setRole("STUDENT");
        if (!Objects.isNull(users.getCollegeId()))
            users.setCollegeId(new ArrayList<>(users.getCollegeId()));
        userRepo.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String verify(LogInReqDTO logInReqDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInReqDTO.getUsername(),logInReqDTO.getPassword()));
        if (auth.isAuthenticated()) {
            return jwtService.generateToken(logInReqDTO.getUsername());
        }
            throw new BadCredentialsException("Bad credentials");
    }
}
