package com.softserve.kickscooter.kickscootervehicle.management.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UiPointDto {
    UUID id;
    double[] coordinates;
    int battery;
}
