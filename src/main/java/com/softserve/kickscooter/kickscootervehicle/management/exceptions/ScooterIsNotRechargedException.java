package com.softserve.kickscooter.kickscootervehicle.management.exceptions;

public class ScooterIsNotRechargedException extends RuntimeException{

    public ScooterIsNotRechargedException(String message){
        super(message);
    }
}
