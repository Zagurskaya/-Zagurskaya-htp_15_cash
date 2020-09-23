package com.zagurskaya.cash.exception;

public class SiteDataValidationException extends Exception {

    public SiteDataValidationException() {
        super();
    }

    public SiteDataValidationException(String message) {
        super(message);
    }

    public SiteDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SiteDataValidationException(Throwable cause) {
        super(cause);
    }

    protected SiteDataValidationException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
