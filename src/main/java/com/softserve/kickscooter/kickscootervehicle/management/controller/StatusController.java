package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/scooters/status")
@AllArgsConstructor
public class StatusController {

    private StatusService statusService;

    //todo: Storing and retrieving actual gps data for every scooter
//    @GetMapping
//    public ResponseEntity<List<ScooterStatusDto>> getWorkingScootersStatus(){
//        List<ScooterStatusDto> statusDto = statusService.getActiveAndFreeScooters();
//        return ResponseEntity.of()
//    }

    @PutMapping("/acquire/{id}")
    public ResponseEntity<String> acquireScooter(UUID id){
        statusService.acquireScooter(id);
        return ResponseEntity.ok("Successful aquire scooter, id" + id);
    }

    @PutMapping("/free/{id}")
    public ResponseEntity<String> freeScooter(UUID id){
        statusService.freeScooter(id);
        return ResponseEntity.ok("Successful aquire scooter, id" + id);

    }

    @PutMapping("/retrieve/{id}")
    public ResponseEntity<String> retrieveScooter(UUID id){
        statusService.retrieveScooter(id);
        return ResponseEntity.ok("Successful aquire scooter, id" + id);
    }

    @PutMapping("/inspect/{id}")
    public ResponseEntity<String> takeScooterOnInspection(UUID id){
        statusService.onInspection(id);
        return ResponseEntity.ok("Successful aquire scooter, id" + id);

    }
}
