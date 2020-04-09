package com.softserve.kickscooter.kickscootervehicle.management.converter;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterRawDataDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

@Component
public class RawDataToScooterStatusDto implements Converter<ScooterRawDataDto, ScooterStatusDto> {

    @Override
    public ScooterStatusDto convert(ScooterRawDataDto rawDto) {
        var statusDto = new ScooterStatusDto();
        statusDto.setId(rawDto.getId());
        statusDto.setGpsPoint(new Point(rawDto.getLatitude(), rawDto.getLongitude()));
        statusDto.setBattery(rawDto.getBattery());
        return statusDto;
    }
}
