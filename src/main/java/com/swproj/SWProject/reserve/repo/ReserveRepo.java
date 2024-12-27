package com.swproj.SWProject.reserve.repo;

import com.swproj.SWProject.projenums.Periodically;
import com.swproj.SWProject.projenums.Status;
import com.swproj.SWProject.reserve.entity.ReserveEntity;
import com.swproj.SWProject.roommangement.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReserveRepo extends JpaRepository<ReserveEntity, Long> {
    @Modifying
    @Query("UPDATE ReserveEntity r " +
            "SET r.status = 'Declined' " +
            "WHERE r.roomEntity = :roomEntity " +
            "AND r.status != 'Declined' " +
            "AND (" +
            "(r.startDate BETWEEN :startDate AND :endDate AND r.startTime BETWEEN :startTime AND :endTime) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate AND r.endTime BETWEEN :startTime AND :endTime) OR " +
            "(r.startDate <= :startDate AND r.endDate >= :endDate AND r.startTime <= :startTime AND r.endTime >= :endTime)" +
            ")" +
            "AND (:periodically = 'None' OR r.periodically = :periodically)")
    void denyOtherReservations(@Param("roomEntity") RoomEntity roomEntity,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate,
                               @Param("startTime") LocalTime startTime,
                               @Param("endTime") LocalTime endTime,
                               @Param("periodically") Periodically periodically);

    List<ReserveEntity> findByStatus(Status status);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM ReserveEntity r " +
            "WHERE r.roomEntity = :roomEntity " +
            "AND r.status = :status " +
            "AND (" +
            "(r.startDate BETWEEN :startDate AND :endDate AND r.startTime BETWEEN :startTime AND :endTime) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate AND r.endTime BETWEEN :startTime AND :endTime) OR " +
            "(r.startDate <= :startDate AND r.endDate >= :endDate AND r.startTime <= :startTime AND r.endTime >= :endTime)" +
            ")" +
            "AND (:periodically = 'None' OR r.periodically = :periodically)")
    boolean checkConflicts(@Param("roomEntity") RoomEntity roomEntity,
                           @Param("status") Status status,
                           @Param("endDate") LocalDate endDate,
                           @Param("startDate") LocalDate startDate,
                           @Param("endTime") LocalTime endTime,
                           @Param("startTime") LocalTime startTime,
                           @Param("periodically") Periodically periodically);
    List<ReserveEntity> findByRoomEntityId(Long roomId);


}
