package com.softserve.kickscooter.kickscootervehicle.management.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Scooter {

    @Id
    @GeneratedValue
    private UUID id;

    private String modelName;

    private String serialNumber;

    @CreatedDate
    private Instant registerDate;

    private Instant expiredDate;

    @Enumerated(EnumType.STRING)
    private ScooterStatus status;

    private Double actualLongitude;

    private Double actualLatitude;

    private Short battery;


}
