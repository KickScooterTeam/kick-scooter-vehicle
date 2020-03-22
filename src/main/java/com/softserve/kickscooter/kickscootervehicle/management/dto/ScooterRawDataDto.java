package com.softserve.kickscooter.kickscootervehicle.management.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ScooterRawDataDto {
    UUID id;
    double latitude;
    double longitude;
    short battery;
}
