package com.softserve.kickscooter.kickscootervehicle.management.dto;

import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.UUID;

@Data
public class ScooterStatusDto {
    UUID id;
    ScooterStatus status;
    Point gpsPoint;
    Short battery;
}
