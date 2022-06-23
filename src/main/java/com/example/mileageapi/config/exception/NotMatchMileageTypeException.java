package com.example.mileageapi.config.exception;

public final class NotMatchMileageTypeException extends RuntimeException {
    public NotMatchMileageTypeException(String name) {
        super("not match mileage type =>" + name);
    }
}
