package com.softserve.kickscooter.kickscootervehicle.management.converter;


import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ScooterCreateDtoToEntity implements Converter<ScooterCreateDto, Scooter> {
    @Override
    public Scooter convert(ScooterCreateDto scooterCreateDto) {
        Scooter scooter = new Scooter();
        scooter.setModelName(scooterCreateDto.getModelName());
        scooter.setSerialNumber(scooterCreateDto.getSerialNumber());
        scooter.setAlive(true);
        return scooter;
    }
}
