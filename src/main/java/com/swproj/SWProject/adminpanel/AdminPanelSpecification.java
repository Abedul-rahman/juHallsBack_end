package com.swproj.SWProject.adminpanel;

import com.swproj.SWProject.config.entity.Users;
import org.springframework.data.jpa.domain.Specification;

public class AdminPanelSpecification {
    public static Specification<Users> containsKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likeKeyword = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), likeKeyword),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), likeKeyword),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("role")), likeKeyword)
            );
        };
    }
}
