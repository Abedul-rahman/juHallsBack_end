package com.swproj.SWProject.roommangement.repo;

import com.swproj.SWProject.roommangement.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<RoomEntity,Long> {
    List<RoomEntity> findByFloorId(Long floorId);

}
