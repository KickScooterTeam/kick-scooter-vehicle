package com.softserve.kickscooter.kickscootervehicle.management.controller;

import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterCreateDto;
import com.softserve.kickscooter.kickscootervehicle.management.dto.ScooterInfoDto;
import com.softserve.kickscooter.kickscootervehicle.management.service.ScooterManagementService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@AutoConfigureWebClient(registerRestTemplate = true)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@RestClientTest({ScooterManagementService.class})
@RequiredArgsConstructor
class ManagementControllerTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private String port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void test(){
        System.out.println(restTemplate);
    }

    @Test
    void getScootersId() {
        ResponseEntity<Iterable<UUID>> response = restTemplate.exchange(getRootUrl() + "/scooter/id",
                HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        assertNotNull(response.getBody());
    }

    @Test
    void getAllScooterInfo() {

        ResponseEntity<Iterable<ScooterInfoDto>> response = restTemplate.exchange(getRootUrl() + "/scooter/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        assertNotNull(response.getBody());
    }

    @Test
    void getScooterInfo() {
        ResponseEntity<ScooterInfoDto> infoDto = restTemplate.getForEntity(getRootUrl() + "/scooter/1", ScooterInfoDto.class);
        System.out.println(infoDto);
        assertNotNull(infoDto.getBody());
    }

    @Test
    void createScooter() {
        ScooterCreateDto createDto = new ScooterCreateDto();

        ResponseEntity<UUID> postResponse = restTemplate.postForEntity(getRootUrl() + "/scooter", createDto, UUID.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void deleteScooter() {
        int id = 1;
        restTemplate.delete(getRootUrl() + "/scooter/" + id, id);

        ResponseEntity<UUID> getResponse = restTemplate.getForEntity(getRootUrl() + "/scooter/" + id, UUID.class);
        assertEquals(getResponse.getStatusCode(), HttpStatus.NO_CONTENT);

    }
}