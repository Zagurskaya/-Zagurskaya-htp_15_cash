package com.zagurskaya.cash.exception;

/**
 * Service constraint violation exception
 */
public class ServiceConstraintViolationException extends Exception {

    /**
     * Constructor
     *
     * @param message - message
     * @param cause   - cause
     */
    public ServiceConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
