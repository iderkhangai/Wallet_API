package com.example.leovegas.exception;

public class TransactionDuplicatedException extends RuntimeException {
    public TransactionDuplicatedException(String message) {
        super(message);
    }

    public TransactionDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionDuplicatedException(Throwable cause) {
        super(cause);
    }
}
