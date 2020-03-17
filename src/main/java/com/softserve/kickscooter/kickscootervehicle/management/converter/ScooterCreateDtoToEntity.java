package com.softserve.kickscooter.kickscootervehicle.management.converter;


import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ScooterCreateDtoToEntity implements Converter<ScooterCreateDto, Scooter> {
    @Override
    public Scooter convert(ScooterCreateDto scooterCreateDto) {
        Scooter scooter = new Scooter();
        scooter.setModelName(scooterCreateDto.getModelName());
        scooter.setSerialId(scooterCreateDto.getSerialId());
        scooter.setRegisterDate(LocalDateTime.now());
        scooter.setIsAlive(true);
        return scooter;
    }
}
