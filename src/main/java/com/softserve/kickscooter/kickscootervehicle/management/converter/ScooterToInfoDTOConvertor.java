package com.softserve.kickscooter.kickscootervehicle.management.converter;


import com.softserve.kickscooter.kickscootervehicle.management.dao.Scooter;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterInfoDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScooterToInfoDTOConvertor implements Converter<Scooter, ScooterInfoDto> {
    @Override
    public ScooterInfoDto convert(Scooter scooter) {
        ScooterInfoDto scooterInfoDto = new ScooterInfoDto();
        scooterInfoDto.setModelName(scooter.getModelName());
        scooterInfoDto.setSerialId(scooter.getSerialId());
        scooterInfoDto.setRegisterDate(scooter.getRegisterDate());
        scooterInfoDto.setExpireDate(scooter.getExpiredDate());
        scooterInfoDto.setId(scooter.getId());
        scooterInfoDto.setIsAlive(scooter.getIsAlive());
        return scooterInfoDto;
    }
}
