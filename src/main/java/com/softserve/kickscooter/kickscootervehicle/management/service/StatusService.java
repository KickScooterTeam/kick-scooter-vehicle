package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    List<ScooterStatusDto> getActiveAndFreeScooters();
    Boolean acquireScooter(UUID id);
    Boolean freeScooter(UUID id);
    Boolean retrieveScooter(UUID id);
    Boolean onInspection(UUID id);
}
