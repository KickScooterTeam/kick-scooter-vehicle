package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.UiPointDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    ScooterStatusDto getScooterStatusDetails(UUID id);

    Boolean acquireScooter(UUID id);
    Boolean freeScooter(UUID id);
    Boolean retrieveScooter(UUID id);
    Boolean onInspection(UUID id);
    ScooterStatus getCurrentStatus(UUID id);
    void saveActualStatusData(ScooterStatusDto dto);

    List<UiPointDto> getFreeScooters();
}
