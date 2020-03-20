package com.softserve.kickscooter.kickscootervehicle.management.converter;


import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
import com.softserve.kickscooter.kickscootervehicle.management.model.Scooter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScooterToTechInfoDTOConverter implements Converter<Scooter, ScooterTechInfoDto> {
    @Override
    public ScooterTechInfoDto convert(Scooter scooter) {
        var scooterTechInfoDto = new ScooterTechInfoDto();
        scooterTechInfoDto.setModelName(scooter.getModelName());
        scooterTechInfoDto.setSerialNumber(scooter.getSerialNumber());
        scooterTechInfoDto.setRegisterDate(scooter.getRegisterDate());
        scooterTechInfoDto.setExpireDate(scooter.getExpiredDate());
        scooterTechInfoDto.setId(scooter.getId());
        scooterTechInfoDto.setStatus(scooter.getStatus());
        return scooterTechInfoDto;
    }
}
