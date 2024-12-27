package com.swproj.SWProject.reserve.repo;

import com.swproj.SWProject.reserve.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepo extends JpaRepository<ReserveEntity,Long> {
}
