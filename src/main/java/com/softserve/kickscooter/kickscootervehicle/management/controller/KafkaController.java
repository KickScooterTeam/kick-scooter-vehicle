package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterRawDataDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterStatusDto;
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

    private static final String STATUS_TOPIC = "all-scooter-data-topic";
    private static final String RAW_DATA = "raw-data";

    private final KafkaTemplate<String, ScooterStatusDto> template;

    private final StatusService statusService;

    //todo: send to active scooter
    //todo: send to free scooter topic
    public void sendStatusDataToTopic(ScooterStatusDto dto){
        log.info("Send data to topic '{}': {} ", STATUS_TOPIC, dto);
        template.send(STATUS_TOPIC, dto);
    }

    @KafkaListener(topics = RAW_DATA)
    public void listen(ScooterRawDataDto rawDto) {
        log.info("Received from '{}' : {}", RAW_DATA, rawDto);
        ScooterStatus status = statusService.getCurrentStatus(rawDto.getId());
        var statusDto = new ScooterStatusDto();
        statusDto.setId(rawDto.getId());
        statusDto.setGpsPoint(new Point(rawDto.getLatitude(),rawDto.getLongitude()));
        statusDto.setStatus(status);
        statusDto.setBattery(rawDto.getBattery());

        statusService.saveActualStatusData(rawDto.getId(),
                rawDto.getLatitude(),
                rawDto.getLongitude(),
                rawDto.getBattery());

        sendStatusDataToTopic(statusDto);

    }

}
