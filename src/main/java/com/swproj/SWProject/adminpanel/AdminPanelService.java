package com.swproj.SWProject.adminpanel;

import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPanelService {

    private final UserRepo userRepo;

    public Page<Users> getUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepo.findAll(AdminPanelSpecification.containsKeyword(keyword), pageable);
    }

    public String setUserRole(Long userId, Long collegeId, String role) {
        Users user = userRepo.findById(Math.toIntExact(userId)).orElseThrow(() -> new RuntimeException("User not found"));

        if ("local_administrator".equalsIgnoreCase(role)) {
            user.setRole("local_administrator");

            if (!user.getCollegeId().contains(collegeId)) {
                user.getCollegeId().add(collegeId);
            }
            userRepo.linkUserToCollege(user.getId(), collegeId);
            userRepo.save(user);
            return "User role set to local administrator for college " + collegeId;
        }

        if ("global_administrator".equalsIgnoreCase(role) || "doctor".equalsIgnoreCase(role)) {
            user.setRole(role);
            userRepo.save(user);
            return "User role set to " + role;
        }

        return "Invalid role provided";
    }
}

