package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterInfoDto;
import com.softserve.kickscooter.kickscootervehicle.management.service.ManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/scooter")
@AllArgsConstructor
public class ManagementController {

    private ManagementService service;

    @GetMapping("/id")
    public ResponseEntity<Iterable<UUID>> getScootersId(){
        log.debug("getScootersId method");
        log.debug(service.toString());
        return new ResponseEntity<>(service.getScootersId(), HttpStatus.OK);
    }

    @GetMapping("/info-all")
    public ResponseEntity<Iterable<ScooterInfoDto>> getAllScooterInfo(){
        log.debug("getAllScootersInfo method");
        return new ResponseEntity<>(service.getAllScooterInfo(), HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ScooterInfoDto> getScooterInfo(@PathVariable UUID id){
        log.debug("getScootersInfo method, uuid = " + id );
        ScooterInfoDto infoDto = service.getScooterInfo(id);
        if(infoDto.getId() != null) {
            return new ResponseEntity<>(infoDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //todo: response if not created
    @PostMapping("/")
    public ResponseEntity<UUID> createScooter(@RequestBody ScooterCreateDto dto){
        Scooter scooter = service.createScooter(dto);
        UUID id = scooter.getId();
        log.debug("inside createScooter method, uuid new scooter" + id);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<UUID> deleteScooter(@PathVariable UUID id){
        log.debug("inside deleteScooter method");
        Boolean result = service.deleteScooter(id);
        if(result) {
            log.debug("successful delete, uuid = " + id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            log.warn("unsuccesful delete, uuid = " + id);
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        }
    }

}
