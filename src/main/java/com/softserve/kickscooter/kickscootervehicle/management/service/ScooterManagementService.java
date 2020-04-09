package com.softserve.kickscooter.kickscootervehicle.management.service;


import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.repository.ScooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScooterManagementService implements ManagementService {

    private ScooterRepository scooterRepo;
    private ConversionService convService;

    public Scooter registerScooter(ScooterCreateDto dto){
        Scooter scooter = convService.convert(dto, Scooter.class);
        scooterRepo.save(scooter);
        return scooter;
    }

    public Optional<ScooterTechInfoDto> getScooterTechInfo(UUID id){
        return scooterRepo.findById(id)
                .map(scooter -> convService.convert(scooter, ScooterTechInfoDto.class));
    }

    public List<ScooterTechInfoDto> getAllScooterTechInfo() {
        return scooterRepo.findAll()
                .stream()
                .map(scooter -> convService.convert(scooter, ScooterTechInfoDto.class))
                .collect(Collectors.toList());
    }

    public boolean utilizeScooter(UUID id) {
        Scooter scooter = scooterRepo.getOne(id);
            if (scooter.getStatus() == ScooterStatus.ON_INSPECTION) {
                scooter.setStatus(ScooterStatus.DECOMMISSIONED);
                scooter.setExpiredDate(Instant.now());
                scooterRepo.save(scooter);
                return true;
            }
        return false;
    }
}
