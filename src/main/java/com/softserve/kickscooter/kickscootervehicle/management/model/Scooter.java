package com.softserve.kickscooter.kickscootervehicle.management.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
//@Table
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


}
