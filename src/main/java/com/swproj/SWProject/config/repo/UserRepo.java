package com.swproj.SWProject.config.repo;

import com.swproj.SWProject.config.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    List<Users> findByCollegeId(Long id);

    Page<Users> findAll(Specification<Users> usersSpecification, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO college_entity_admins (admins_id, college_entity_id) VALUES (:userId, :collegeId)", nativeQuery = true)
    void linkUserToCollege(@Param("userId") Long userId, @Param("collegeId") Long collegeId);


    @Query("SELECT u FROM Users u JOIN u.collegeId c WHERE c = :collegeId")
    List<Users> findByCollegeIdComplex(@Param("collegeId") Long collegeId);

}
