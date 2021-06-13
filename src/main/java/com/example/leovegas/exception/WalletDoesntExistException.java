package com.example.leovegas.exception;

public class WalletDoesntExistException extends RuntimeException {
    public WalletDoesntExistException(String message) {
        super(message);
    }

    public WalletDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletDoesntExistException(Throwable cause) {
        super(cause);
    }
}
