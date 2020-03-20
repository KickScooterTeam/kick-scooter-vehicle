package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;

import java.util.Optional;
import java.util.UUID;

public interface ManagementService {
    Iterable<ScooterTechInfoDto> getAllScooterTechInfo();
    Optional<ScooterTechInfoDto> getScooterTechInfo(UUID id);
    Scooter registerScooter(ScooterCreateDto dto);
    Boolean utilizeScooter(UUID id);
}
