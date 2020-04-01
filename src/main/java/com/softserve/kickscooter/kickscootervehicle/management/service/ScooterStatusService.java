package com.softserve.kickscooter.kickscootervehicle.management.service;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDecommisionedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsNotRechargedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterNotFoundException;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.repository.ScooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScooterStatusService implements StatusService {
    private ScooterRepository scooterRepo;
    private ConversionService convService;

    public ScooterStatusDto getScooterStatusDetails(UUID id){
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
        return convService.convert(scooter, ScooterStatusDto.class);
    }

    public Boolean acquireScooter(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
        scooter.setStatus(ScooterStatus.IN_USE);
        scooterRepo.save(scooter);
        return true;
    }

    public Boolean freeScooter(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
        scooter.setStatus(ScooterStatus.FREE);
        scooterRepo.save(scooter);
        return true;
    }

    public Boolean retrieveScooter(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
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
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
        scooter.setStatus(ScooterStatus.ON_INSPECTION);
        scooterRepo.save(scooter);
        return true;
    }

    @Transactional
    public ScooterStatus getCurrentStatus(UUID id) {
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        if(scooter.getStatus() == ScooterStatus.DECOMMISSIONED){
            throw new ScooterIsDecommisionedException();
        }
        return scooter.getStatus();
    }

    @Transactional
    public void saveActualStatusData(UUID id, double latitude, double longitude, short battery){
        Scooter scooter = scooterRepo.findById(id).orElseThrow(ScooterNotFoundException::new);
        scooter.setActualLatitude(latitude);
        scooter.setActualLongitude(longitude);
        scooter.setBattery(battery);
        scooterRepo.save(scooter);
    }

}
