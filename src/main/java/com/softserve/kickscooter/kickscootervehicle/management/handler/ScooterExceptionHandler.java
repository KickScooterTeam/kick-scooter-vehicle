package com.softserve.kickscooter.kickscootervehicle.management.handler;

import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsDecommisionedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterIsNotRechargedException;
import com.softserve.kickscooter.kickscootervehicle.management.exceptions.ScooterNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ScooterExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ScooterNotFoundException.class, ScooterIsDecommisionedException.class})
    public ResponseEntity<String> noScooterFound(Exception e) {
        return ResponseEntity.status(404).body("Scooter with requested id not found");
    }

    @ExceptionHandler(ScooterIsNotRechargedException.class)
    public ResponseEntity<String> scooterDischarged(Exception e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }

}
