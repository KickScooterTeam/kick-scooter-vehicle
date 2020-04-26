package com.softserve.kickscooter.kickscootervehicle.management.repository;

import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScooterRepository extends JpaRepository<Scooter, UUID> {
    List<Scooter> findByStatus(ScooterStatus status);
}
