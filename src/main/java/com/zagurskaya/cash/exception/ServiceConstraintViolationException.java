package com.zagurskaya.cash.exception;

public class ServiceConstraintViolationException extends Exception {

    public ServiceConstraintViolationException() {
        super();
    }

    public ServiceConstraintViolationException(String message) {
        super(message);
    }

    public ServiceConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceConstraintViolationException(Throwable cause) {
        super(cause);
    }

    protected ServiceConstraintViolationException(String message, Throwable cause,
                                                  boolean enableSuppression,
                                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
