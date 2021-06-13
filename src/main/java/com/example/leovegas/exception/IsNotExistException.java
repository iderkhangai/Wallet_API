package com.example.leovegas.exception;

public class IsNotExistException extends RuntimeException {

    public IsNotExistException(String message) {
        super(message);
    }

    public IsNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IsNotExistException(Throwable cause) {
        super(cause);
    }
}
