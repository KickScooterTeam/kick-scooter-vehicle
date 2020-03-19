package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterTechInfoDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ManagementEndToEndTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getScootersId() {
        ResponseEntity<Iterable<UUID>> response = restTemplate.exchange( "/scooter/id",
                HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    void getAllScooterInfo() {
        ResponseEntity<Iterable<ScooterTechInfoDto>> response = restTemplate.exchange("/scooter/info-all",
                HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void getScooterInfo_NonExist() {
        String url = String.format( "/scooter/info/%s", UUID.randomUUID());
        ResponseEntity<ScooterTechInfoDto> response = restTemplate.getForEntity(url, ScooterTechInfoDto.class);
        assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode());
        //assertNull(response.getBody());
    }

    @Disabled //test works,but incorrect deserializing UUID inside
    @Test
    void createScooter() {
        ScooterCreateDto createDto = new ScooterCreateDto();
        createDto.setModelName("string");
        createDto.setSerialNumber("id-id");
        ResponseEntity<UUID> postResponse = restTemplate.postForEntity("/scooter", createDto, UUID.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.CREATED);
        System.out.println(postResponse.getBody());
        assertNotNull(postResponse.getBody());
    }

    @Disabled//test works,but incorrect deserializing UUID inside
    @Test
    void deleteScooterNonExist() {
        UUID id = UUID.randomUUID();
        restTemplate.delete("/scooter/" + id);

        ResponseEntity<UUID> getResponse = restTemplate.getForEntity("/scooter/" + id, UUID.class);
        assertEquals(getResponse.getStatusCode(), HttpStatus.NOT_FOUND);

    }
}