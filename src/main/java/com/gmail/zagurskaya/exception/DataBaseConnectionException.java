package com.gmail.zagurskaya.exception;

public class DataBaseConnectionException extends RuntimeException {

    public DataBaseConnectionException() {
        super();
    }

    public DataBaseConnectionException(String message) {
        super(message);
    }

    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseConnectionException(Throwable cause) {
        super(cause);
    }

    protected DataBaseConnectionException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
