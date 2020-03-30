package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterRawDataDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDecommisionedException;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaController {

    private static final String IN_USE_TOPIC = "in-use-scooter-data";
    private static final String FREE_TOPIC = "free-scooter-data";
    private static final String RAW_DATA = "raw-data";

    private final KafkaTemplate<String, ScooterStatusDto> template;

    private final StatusService statusService;

    public void sendInUseStatusDataToTopic(ScooterStatusDto dto){
        log.info("Send data to topic '{}': {} ", IN_USE_TOPIC, dto);
        template.send(IN_USE_TOPIC, dto);
    }

    public void sendFreeStatusDataToTopic(ScooterStatusDto dto){
        log.info("Send data to topic '{}': {} ", FREE_TOPIC, dto);
        template.send(FREE_TOPIC, dto);
    }
    
    @KafkaListener(topics = RAW_DATA)
    public void listen(ScooterRawDataDto rawDto) {
        log.info("Received from '{}' : {}", RAW_DATA, rawDto);
        try {
            ScooterStatus status = statusService.getCurrentStatus(rawDto.getId());
            var statusDto = new ScooterStatusDto();
            statusDto.setId(rawDto.getId());
            statusDto.setGpsPoint(new Point(rawDto.getLatitude(), rawDto.getLongitude()));
            statusDto.setStatus(status);
            statusDto.setBattery(rawDto.getBattery());

            statusService.saveActualStatusData(rawDto.getId(),
                    rawDto.getLatitude(),
                    rawDto.getLongitude(),
                    rawDto.getBattery());

            if(statusDto.getStatus() == ScooterStatus.IN_USE) {
                sendInUseStatusDataToTopic(statusDto);
            } else if (statusDto.getStatus() == ScooterStatus.FREE){
                sendFreeStatusDataToTopic(statusDto);
            }
        } catch (ScooterIsDecommisionedException e){
            log.error("Received from DECOMMISSIONED scooter '{}' : {}", RAW_DATA, rawDto);
        }

    }

}
