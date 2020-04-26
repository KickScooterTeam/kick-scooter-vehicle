package com.softserve.kickscooter.kickscootervehicle.management.converter;

import com.softserve.kickscooter.kickscootervehicle.management.dto.UiPointDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScooterToUiPointDtoConverter implements Converter<Scooter, UiPointDto> {
    @Override
    public UiPointDto convert(Scooter scooter) {
        var point = new UiPointDto();
        point.setId(scooter.getId());
        point.setCoordinates(new double[]{scooter.getActualLatitude(), scooter.getActualLongitude()});
        point.setBattery(scooter.getBattery());
        return point;
    }
}
