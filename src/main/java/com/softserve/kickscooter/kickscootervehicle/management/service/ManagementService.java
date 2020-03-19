package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface ManagementService {
    Iterable<UUID> getScootersId();
    Iterable<ScooterTechInfoDto> getAllScooterInfo();
    Optional<ScooterTechInfoDto> getScooterInfo(UUID id);
    Scooter createScooter(ScooterCreateDto dto);
    Boolean deleteScooter(UUID id);
}
