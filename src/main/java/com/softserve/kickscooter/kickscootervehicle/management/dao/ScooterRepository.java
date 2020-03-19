package com.softserve.kickscooter.kickscootervehicle.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface ScooterRepository extends JpaRepository<Scooter, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Scooter s SET s.alive = false WHERE s.id = ?1")
    Integer excludeById(UUID id);

    @Query("SELECT s.id FROM Scooter s WHERE s.alive = true")
    List<UUID> getAliveIds();

}
