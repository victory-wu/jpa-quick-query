package com.victor.wu.exception;


/**
 *
 */
public class IncorrectFormatException extends RuntimeException {
    public IncorrectFormatException() {
    }

    public IncorrectFormatException(String message) {
        super(message);
    }

    public IncorrectFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
