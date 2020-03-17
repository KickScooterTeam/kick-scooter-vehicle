package com.softserve.kickscooter.kickscootervehicle.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScooterInfoDto {
    UUID id;
    String modelName;
    String serialId;
    LocalDateTime registerDate;
    LocalDateTime expireDate;
    Boolean isAlive;

}