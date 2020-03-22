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

    private static final String statusTopic = "all_scooter_data_topic";
    private static final String rawDataTopic = "raw-data";

    private final KafkaTemplate<String, ScooterStatusDto> template;

    private StatusService statusService;

    public void sendStatusDataToTopic(ScooterStatusDto dto){
        log.info("Send data to topic " + statusTopic + " {}", dto);
        template.send(statusTopic, dto);
    }

    //todo: perisist in this method dump of gps/battery status
    @KafkaListener(topics = rawDataTopic)
    public void listen(ScooterRawDataDto rawDto) {
        log.info("Received from " + rawDataTopic + " {}", rawDto);
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
