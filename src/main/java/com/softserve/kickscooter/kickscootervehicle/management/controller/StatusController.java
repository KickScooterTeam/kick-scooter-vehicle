package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.UiPointDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/scooters/status")
@AllArgsConstructor
public class StatusController {

    private StatusService statusService;

    @GetMapping("/free")
    public ResponseEntity<List<UiPointDto>> getFreeScooters(){
        return ResponseEntity.ok(statusService.getFreeScooters());
    }

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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/acquire")
    public ResponseEntity<String> acquireScooter(@PathVariable UUID id){
        statusService.acquireScooter(id);
        return ResponseEntity.ok("Successful aquire scooter, id " + id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/free")
    public ResponseEntity<String> freeScooter(@PathVariable UUID id){
        statusService.freeScooter(id);
        return ResponseEntity.ok("Successful free scooter, id " + id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/retrieve")
    public ResponseEntity<String> retrieveScooter(@PathVariable UUID id){
        statusService.retrieveScooter(id);
        return ResponseEntity.ok("Successful retrieve scooter, id " + id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/inspect")
    public ResponseEntity<String> takeScooterOnInspection(@PathVariable UUID id){
        statusService.onInspection(id);
        return ResponseEntity.ok("Successful take scooter on inspection, id " + id);

    }
}
