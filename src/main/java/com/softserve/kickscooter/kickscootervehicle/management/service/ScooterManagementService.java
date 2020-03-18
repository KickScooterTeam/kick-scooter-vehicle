package com.softserve.kickscooter.kickscootervehicle.management.service;


import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dao.ScooterRepository;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScooterManagementService implements ManagementService {

    private ScooterRepository scooterRepo;
    private ConversionService convService;
    //private ScooterStatusService statusService;

    public Scooter createScooter(ScooterCreateDto dto){
        Scooter scooter = convService.convert(dto, Scooter.class);
        scooterRepo.save(scooter);
        //UUID id = scooter.getId();
        //statusService.newScooter(id);
        return scooter;
    }

    public Optional<ScooterInfoDto> getScooterInfo(UUID id){
        Optional<Scooter> optSc = scooterRepo.findById(id);
        if(optSc.isPresent()) {
            Scooter scooter = optSc.get();
            return Optional.ofNullable(convService.convert(scooter, ScooterInfoDto.class));
        } else {
            return Optional.empty();
        }

    }

    //todo: pagination
    public Iterable<ScooterInfoDto> getAllScooterInfo(){
        Iterable<Scooter> scooters = scooterRepo.findAll();
        ArrayList<ScooterInfoDto> result = new ArrayList<>();
        for(Scooter sc : scooters) {
            result.add(convService.convert(sc, ScooterInfoDto.class));
        }
        return result;
    }

    //todo: custom query
    public Iterable<UUID> getScootersId(){
        return scooterRepo.getAliveIds();
    }


    public Boolean deleteScooter(UUID id){
        boolean present = scooterRepo.findById(id).isPresent();
        if(present) {
            scooterRepo.excludeById(id);
            //   statusService.deleteScooter(id);
            return true;
        } else {
            return false;
        }
    }

}
