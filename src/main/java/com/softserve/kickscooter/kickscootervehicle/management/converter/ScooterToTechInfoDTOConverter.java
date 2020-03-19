package com.softserve.kickscooter.kickscootervehicle.management.converter;


import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
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
        scooterTechInfoDto.setIsAlive(scooter.getAlive());
        return scooterTechInfoDto;
    }
}
