package com.example.springrestful.exception;

public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }
}
