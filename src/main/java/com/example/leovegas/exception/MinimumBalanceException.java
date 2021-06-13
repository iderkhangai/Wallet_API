package com.example.leovegas.exception;

public class MinimumBalanceException extends RuntimeException {
    public MinimumBalanceException(String message) {
        super(message);
    }

    public MinimumBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinimumBalanceException(Throwable cause) {
        super(cause);
    }
}
