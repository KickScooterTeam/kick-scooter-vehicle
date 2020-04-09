package com.softserve.kickscooter.kickscootervehicle.management.converter;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

@Component
public class ScooterToScooterStatusDto implements Converter<Scooter, ScooterStatusDto> {

    @Override
    public ScooterStatusDto convert(Scooter scooter) {
        var dto = new ScooterStatusDto();
        dto.setId(scooter.getId());
        dto.setStatus(scooter.getStatus());
        dto.setGpsPoint(new Point(scooter.getActualLatitude(), scooter.getActualLongitude()));
        dto.setBattery(scooter.getBattery());
        return dto;
    }
}

