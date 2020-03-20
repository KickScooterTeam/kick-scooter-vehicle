package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
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
    public ResponseEntity<Iterable<ScooterTechInfoDto>> getAllScooterTechInfo(){
        return ResponseEntity.ok(service.getAllScooterTechInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScooterTechInfoDto> getScooterTechInfo(@PathVariable UUID id){
        log.debug("Retrieving scooter by id = " + id );
        Optional<ScooterTechInfoDto> infoDto = service.getScooterTechInfo(id);
        return infoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UUID> registerScooter(@RequestBody ScooterCreateDto dto){
        Scooter scooter = service.registerScooter(dto);
        UUID id = scooter.getId();
        log.info("New scooter registred, uuid of new scooter {}", id);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> utilizeScooter(@PathVariable UUID id){
        log.info("Utilizing scooter with uuid = {}", id );
        Boolean result = service.utilizeScooter(id);
        if(result) {
            log.debug("Successful removing scooter, uuid = {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Unsuccesful removing scooter, uuid = {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
