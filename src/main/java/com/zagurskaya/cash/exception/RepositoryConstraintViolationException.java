package com.zagurskaya.cash.exception;

public class RepositoryConstraintViolationException extends Exception {

    public RepositoryConstraintViolationException() {
        super();
    }

    public RepositoryConstraintViolationException(String message) {
        super(message);
    }

    public RepositoryConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryConstraintViolationException(Throwable cause) {
        super(cause);
    }

    protected RepositoryConstraintViolationException(String message, Throwable cause,
                                                     boolean enableSuppression,
                                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
