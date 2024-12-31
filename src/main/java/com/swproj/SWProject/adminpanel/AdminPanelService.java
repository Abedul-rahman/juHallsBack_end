package com.swproj.SWProject.adminpanel;

import com.swproj.SWProject.config.entity.UserPrincipal;
import com.swproj.SWProject.config.entity.Users;
import com.swproj.SWProject.config.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPanelService {

    private final UserRepo userRepo;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public Page<Users> getUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepo.findAll(AdminPanelSpecification.containsKeyword(keyword), pageable);
    }

    public String setUserRole(Long userId, Long collegeId, String role) {
        String currentUserRole = getCurrentUserRole();
        List<Long> currentUserColleges = getCurrentUserCollegesFromDb();

        if ("doctor".equals(currentUserRole) || "student".equals(currentUserRole)) {
            return "not permitted to be here";
        }
        if ("local_administrator".equalsIgnoreCase(currentUserRole)) {
            if (!currentUserColleges.contains(collegeId)) {
                return "You are not authorized to modify users outside your college.";
            }

            if ("global_administrator".equalsIgnoreCase(role)) {
                return "You are not authorized to set the role to global administrator.";
            }
        }

        Users user = userRepo.findById(Math.toIntExact(userId)).orElseThrow(() -> new RuntimeException("User not found"));

        if ("local_administrator".equalsIgnoreCase(role)) {
            user.setRole("local_administrator");
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
    private String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users users = userPrincipal.getUser();
            return users.getRole();
        }
        throw new RuntimeException("Unable to determine user role");
    }

    private List<Long> getCurrentUserCollegesFromDb() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users users = userPrincipal.getUser();
            return users.getCollegeId();
        }
        throw new RuntimeException("Unable to determine user colleges");
    }



}


