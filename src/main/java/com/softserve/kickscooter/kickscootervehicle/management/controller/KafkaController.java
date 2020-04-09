package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterRawDataDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDecommisionedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterNotFoundException;
import com.softserve.kickscooter.kickscootervehicle.management.model.ScooterStatus;
import com.softserve.kickscooter.kickscootervehicle.management.service.StatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
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

    private KafkaTemplate<String, ScooterStatusDto> template;

    private StatusService statusService;
    private ConversionService convServ;

    private void sendInUseStatusDataToTopic(ScooterStatusDto dto){
        log.info("Send data to topic '{}': {} ", IN_USE_TOPIC, dto);
        template.send(IN_USE_TOPIC, dto);
    }

    private void sendFreeStatusDataToTopic(ScooterStatusDto dto){
        log.info("Send data to topic '{}': {} ", FREE_TOPIC, dto);
        template.send(FREE_TOPIC, dto);
    }
    
    @KafkaListener(topics = RAW_DATA)
    public void listen(ScooterRawDataDto rawDto) {
        log.info("Received from '{}' : {}", RAW_DATA, rawDto);
        try {
            var statusDto = convServ.convert(rawDto, ScooterStatusDto.class);
            ScooterStatus status = statusService.getCurrentStatus(rawDto.getId());
            statusDto.setStatus(status);
            statusService.saveActualStatusData(statusDto);

            if(statusDto.getStatus() == ScooterStatus.IN_USE) {
                sendInUseStatusDataToTopic(statusDto);
            } else if (statusDto.getStatus() == ScooterStatus.FREE){
                sendFreeStatusDataToTopic(statusDto);
            }
        } catch (ScooterIsDecommisionedException e){
            log.error("Received from DECOMMISSIONED scooter '{}' : {}", RAW_DATA, rawDto);
        } catch (ScooterNotFoundException e){
            log.error("Received from non-exist scooter '{}' : {}", RAW_DATA, rawDto);
        }
    }
}
