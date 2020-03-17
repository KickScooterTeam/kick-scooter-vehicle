package com.softserve.kickscooter.kickscootervehicle.management.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public interface ScooterRepository extends PagingAndSortingRepository<Scooter, UUID> {
    //Iterable<Scooter> findAllById(Iterable<UUID> iterable);

    @Transactional
    @Modifying
    @Query("UPDATE Scooter s SET s.isAlive = false WHERE s.id = ?1")
    Integer excludeById(UUID id);

    @Query("SELECT s.id FROM Scooter s WHERE s.isAlive = true")
    Iterable<UUID> getAliveIds();

}
