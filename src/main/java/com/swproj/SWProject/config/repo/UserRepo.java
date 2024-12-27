package com.swproj.SWProject.config.repo;

import com.swproj.SWProject.config.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    List<Users> findByCollegeId(Long id);

    Page<Users> findAll(Specification<Users> usersSpecification, Pageable pageable);
}
