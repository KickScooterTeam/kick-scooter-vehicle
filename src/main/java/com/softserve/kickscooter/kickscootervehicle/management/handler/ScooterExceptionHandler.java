package com.softserve.kickscooter.kickscootervehicle.management.handler;

import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDecommisionedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ScooterExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, ScooterIsDecommisionedException.class})
    public ResponseEntity<String> noScooterFound(Exception e) {
        return ResponseEntity.status(404).body("Scooter with requested id not found");
    }

}
