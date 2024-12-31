package com.swproj.SWProject.config.service;

import com.swproj.SWProject.config.entity.UserPrincipal;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserdetailServiceProj implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }
}
