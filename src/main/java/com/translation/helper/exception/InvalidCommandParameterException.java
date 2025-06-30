package com.translation.helper.exception;

public class InvalidCommandParameterException extends RuntimeException {
    public InvalidCommandParameterException(String message) {
        super(message);
    }
}
