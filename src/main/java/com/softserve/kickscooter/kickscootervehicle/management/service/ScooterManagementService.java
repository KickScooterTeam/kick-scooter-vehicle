package com.softserve.kickscooter.kickscootervehicle.management.service;


import com.softserve.kickscooter.kickscootervehicle.management.converter.ScooterCreateDtoToEntity;
import com.softserve.kickscooter.kickscootervehicle.management.converter.ScooterToInfoDTOConvertor;
import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dao.ScooterRepository;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScooterManagementService implements ManagementService {

    private ScooterRepository scooterRepo;
    private ScooterToInfoDTOConvertor EntityToDtoConv;
    private ScooterCreateDtoToEntity DtoToEntityConv;
    //private ScooterStatusService statusService;

    public Scooter createScooter(ScooterCreateDto dto){
        Scooter scooter = DtoToEntityConv.convert(dto);
        scooterRepo.save(scooter);
        UUID id = scooter.getId();
        //statusService.newScooter(id);
        return scooter;
    }

    public ScooterInfoDto getScooterInfo(UUID id){
        Optional<Scooter> optSc = scooterRepo.findById(id);
        if(optSc.isPresent()) {
            Scooter scooter = optSc.get();
            return EntityToDtoConv.convert(scooter);
        } else {
            return null;
        }
    }

    //todo: pagination
    public Iterable<ScooterInfoDto> getAllScooterInfo(){
        Iterable<Scooter> scooters = scooterRepo.findAll();
        ArrayList<ScooterInfoDto> result = new ArrayList<>();
        for(Scooter sc : scooters) {
            result.add(EntityToDtoConv.convert(sc));
        }
        return result;
    }

    //todo: custom query
    public Iterable<UUID> getScootersId(){
        Iterable<UUID> ids = scooterRepo.getAliveIds();
        if (ids != null) {
            return ids;
        } else {
            return Collections.EMPTY_LIST;
        }
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
