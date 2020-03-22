package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDeadException;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.repository.ScooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScooterStatusService implements StatusService {

    private ScooterRepository scooterRepo;
    private ConversionService convService;

    @Override
    public List<ScooterStatusDto> getActiveAndFreeScooters() {
        return null;
    }

    @Override
    public ScooterStatusDto getScooterStatusDetails(UUID id){
        Scooter scooter = scooterRepo.getOne(id);
        if(scooter.getStatus() == ScooterStatus.BROKEN){
            throw new ScooterIsDeadException();
        }
        return convService.convert(scooter, ScooterStatusDto.class);
    }

    @Override
    public Boolean acquireScooter(UUID id) {
        Scooter scooter = scooterRepo.getOne(id);
        if(scooter.getStatus() == ScooterStatus.BROKEN){
            throw new ScooterIsDeadException();
        }
        scooter.setStatus(ScooterStatus.ACTIVE);
        scooterRepo.save(scooter);
        return true;
    }

    @Override
    public Boolean freeScooter(UUID id) {
        Scooter scooter = scooterRepo.getOne(id);
        if(scooter.getStatus() == ScooterStatus.BROKEN){
            throw new ScooterIsDeadException();
        }
        scooter.setStatus(ScooterStatus.FREE);
        scooterRepo.save(scooter);
        return true;
    }

    @Override
    public Boolean retrieveScooter(UUID id) {
        Scooter scooter = scooterRepo.getOne(id);
        if(scooter.getStatus() == ScooterStatus.BROKEN){
            throw new ScooterIsDeadException();
        }
        scooter.setStatus(ScooterStatus.FREE);
        scooterRepo.save(scooter);
        return true;
    }

    @Override
    public Boolean onInspection(UUID id) {
        Scooter scooter = scooterRepo.getOne(id);
        if(scooter.getStatus() == ScooterStatus.BROKEN){
            throw new ScooterIsDeadException();
        }
        scooter.setStatus(ScooterStatus.ON_INSPECTION);
        scooterRepo.save(scooter);
        return true;
    }

    @Override
    public ScooterStatus getCurrentStatus(UUID id) {
        Scooter scooter = scooterRepo.getOne(id);
        if(scooter.getStatus() == ScooterStatus.BROKEN){
            throw new ScooterIsDeadException();
        }
        return scooter.getStatus();
    }

    @Override
    public void saveActualStatusData(UUID id, double latitude, double longitude){
        Scooter scooter = scooterRepo.getOne(id);
        scooter.setActualLatitude(latitude);
        scooter.setActualLongitude(longitude);
        scooterRepo.save(scooter);
    }

    @Override
    public void saveActualStatusData(UUID id, double latitude, double longitude, short battery){
        Scooter scooter = scooterRepo.getOne(id);
        scooter.setActualLatitude(latitude);
        scooter.setActualLongitude(longitude);
        scooter.setBattery(battery);
        scooterRepo.save(scooter);
    }

}
