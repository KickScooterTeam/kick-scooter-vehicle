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

import java.util.Optional;
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
        return ResponseEntity.ok(service.getScootersId());
    }

    @GetMapping("/info-all")
    public ResponseEntity<Iterable<ScooterInfoDto>> getAllScooterInfo(){
        log.debug("getAllScootersInfo method");
        return ResponseEntity.ok(service.getAllScooterInfo());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ScooterInfoDto> getScooterInfo(@PathVariable UUID id){
        log.debug("getScootersInfo method, uuid = " + id );
        Optional<ScooterInfoDto> infoDto = service.getScooterInfo(id);
        return infoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //todo: response if not created
    @PostMapping("/")
    public ResponseEntity<UUID> createScooter(@RequestBody ScooterCreateDto dto){
        Scooter scooter = service.createScooter(dto);
        UUID id = scooter.getId();
        log.debug("Inside createScooter method, uuid new scooter" + id);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<UUID> deleteScooter(@PathVariable UUID id){
        log.debug("Inside deleteScooter method");
        Boolean result = service.deleteScooter(id);
        if(result) {
            log.debug("successful delete, uuid = " + id);
            return ResponseEntity.ok(id);
        } else {
            log.debug("unsuccesful delete, uuid = " + id);
            return ResponseEntity.notFound().build();
        }
    }

}
