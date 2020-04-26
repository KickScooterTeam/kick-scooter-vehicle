package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.UiPointDto;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDecommisionedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsNotRechargedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterNotFoundException;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.repository.ScooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScooterStatusService implements StatusService {
    private ScooterRepository scooterRepo;
    private ConversionService convService;

    public ScooterStatusDto getScooterStatusDetails(UUID id){
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        checkDecomissioned(scooter);
        return convService.convert(scooter, ScooterStatusDto.class);
    }

    public Boolean acquireScooter(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        checkDecomissioned(scooter);
        scooter.setStatus(ScooterStatus.IN_USE);
        scooterRepo.save(scooter);
        return true;
    }

    public Boolean freeScooter(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        checkDecomissioned(scooter);
        scooter.setStatus(ScooterStatus.FREE);
        scooterRepo.save(scooter);
        return true;
    }

    public Boolean retrieveScooter(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        checkDecomissioned(scooter);
        if(scooter.getBattery() < 50){
            throw new ScooterIsNotRechargedException("Scooter battery must be charged more than 50%. " +
                    "Current battery: " + scooter.getBattery());
        }
        scooter.setStatus(ScooterStatus.FREE);
        scooterRepo.save(scooter);
        return true;
    }

    public Boolean onInspection(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        checkDecomissioned(scooter);
        scooter.setStatus(ScooterStatus.ON_INSPECTION);
        scooterRepo.save(scooter);
        return true;
    }

    public ScooterStatus getCurrentStatus(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        checkDecomissioned(scooter);
        return scooter.getStatus();
    }

    public void saveActualStatusData(ScooterStatusDto dto){
        Scooter scooter = scooterRepo.findById(dto.getId()).orElseThrow(ScooterNotFoundException::new);
        scooter.setActualLatitude(dto.getGpsPoint().getX());
        scooter.setActualLongitude(dto.getGpsPoint().getY());
        scooter.setBattery(dto.getBattery());
        scooterRepo.save(scooter);
    }

    @Override
    public List<UiPointDto> getFreeScooters() {
        return scooterRepo
                .findByStatus(ScooterStatus.FREE)
                .stream()
                .map(scooter -> convService.convert(scooter, UiPointDto.class))
                .collect(Collectors.toList());
    }

    private void checkDecomissioned(Scooter scooter){
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
    }

}
