package com.swproj.SWProject.roommangement.repo;

import com.swproj.SWProject.roommangement.entity.FloorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorRepo extends JpaRepository<FloorEntity, Long> {

    List<FloorEntity> findByCollegeId(Long collegId );
}
