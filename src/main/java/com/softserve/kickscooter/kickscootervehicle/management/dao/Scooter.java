package com.softserve.kickscooter.kickscootervehicle.management.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scooter")
public class Scooter {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue
    private UUID id;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "serial_number")
    private String serialId;

    @Column(name = "alive")
    private Boolean isAlive;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;


}
