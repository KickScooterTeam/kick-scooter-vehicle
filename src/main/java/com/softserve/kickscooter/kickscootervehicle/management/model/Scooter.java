package com.softserve.kickscooter.kickscootervehicle.management.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime registerDate;

    private LocalDate expiredDate;

    @Enumerated(EnumType.STRING)
    private ScooterStatus status;

    private Double actualLongitude;

    private Double actualLatitude;

    @Min(value = 0, message = "The battery charge must not be less than 0%.")
    @Max(value = 100, message = "The battery charge must not exceed than 100%.")
    private Short battery;


}
