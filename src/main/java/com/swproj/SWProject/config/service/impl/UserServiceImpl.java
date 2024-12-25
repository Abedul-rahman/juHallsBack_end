package com.swproj.SWProject.config.service.impl;

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

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Override
    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("STUDENT");
        userRepo.save(user);
        return user;
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
