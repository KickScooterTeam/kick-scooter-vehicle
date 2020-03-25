package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/scooters/status")
@AllArgsConstructor
public class StatusController {

    private StatusService statusService;

    @GetMapping("/{id}/details")
    public ResponseEntity<ScooterStatusDto> getScooterStatusDetails(@PathVariable UUID id){
        ScooterStatusDto status = statusService.getScooterStatusDetails(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScooterStatus> getScooterStatus(@PathVariable UUID id){
        ScooterStatus status = statusService.getCurrentStatus(id);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/acquire/{id}")
    public ResponseEntity<String> acquireScooter(@PathVariable UUID id){
        statusService.acquireScooter(id);
        return ResponseEntity.ok("Successful aquire scooter, id " + id);
    }

    @PutMapping("/free/{id}")
    public ResponseEntity<String> freeScooter(@PathVariable UUID id){
        statusService.freeScooter(id);
        return ResponseEntity.ok("Successful free scooter, id " + id);
    }

    @PutMapping("/retrieve/{id}")
    public ResponseEntity<String> retrieveScooter(@PathVariable UUID id){
        statusService.retrieveScooter(id);
        return ResponseEntity.ok("Successful retrieve scooter, id " + id);
    }

    @PutMapping("/inspect/{id}")
    public ResponseEntity<String> takeScooterOnInspection(@PathVariable UUID id){
        statusService.onInspection(id);
        return ResponseEntity.ok("Successful take scooter on inspection, id " + id);

    }
}
