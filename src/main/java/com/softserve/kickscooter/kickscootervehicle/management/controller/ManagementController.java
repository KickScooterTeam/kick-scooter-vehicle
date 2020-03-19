package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
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
@RequestMapping("/scooters")
@AllArgsConstructor
public class ManagementController {

    private ManagementService service;

    @GetMapping
    public ResponseEntity<Iterable<ScooterTechInfoDto>> getAllScooterInfo(){
        return ResponseEntity.ok(service.getAllScooterInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScooterTechInfoDto> getScooterInfo(@PathVariable UUID id){
        log.debug("Retrieving scooter by id = " + id );
        Optional<ScooterTechInfoDto> infoDto = service.getScooterInfo(id);
        return infoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //todo: response if not created
    @PostMapping
    public ResponseEntity<UUID> createScooter(@RequestBody ScooterCreateDto dto){
        Scooter scooter = service.createScooter(dto);
        UUID id = scooter.getId();
        log.debug("New scooter registred, uuid of new scooter {}", id);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable UUID id){
        log.debug("Inside deleteScooter method");
        Boolean result = service.deleteScooter(id);
        if(result) {
            log.debug("Successful removing scooter, uuid = {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Unsuccesful removing scooter, uuid = {}", id);
            return ResponseEntity.notFound().build();
        }
    }

}
