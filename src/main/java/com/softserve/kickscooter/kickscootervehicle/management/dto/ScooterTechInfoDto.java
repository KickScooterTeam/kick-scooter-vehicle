package com.softserve.kickscooter.kickscootervehicle.management.dto;

import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ScooterTechInfoDto {
    private UUID id;
    private String modelName;
    private String serialNumber;
    private LocalDateTime registerDate;
    private LocalDate expireDate;
    private ScooterStatus status;

}