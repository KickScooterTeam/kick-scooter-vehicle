package com.softserve.kickscooter.kickscootervehicle.management.dto;

import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ScooterTechInfoDto {
    private UUID id;
    private String modelName;
    private String serialNumber;
    private ZonedDateTime registerDate;
    private ZonedDateTime expireDate;
    private ScooterStatus status;

}