package com.softserve.kickscooter.kickscootervehicle.management.dao;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Scooter {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    private String modelName;

    @Column
    private String serialNumber;

    @Column
    private Boolean alive;

    @CreatedDate
    @Column(nullable = false)//, columnDefinition = "TIMESTAMP")
    private LocalDateTime registerDate;

    //@CreatedDate
    @Column//(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate expiredDate;


}
