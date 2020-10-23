package com.zagurskaya.cash.exception;

/**
 * Ошибка сервиса
 */
public class ServiceException extends Exception {
    /**
     * Constructor
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message - message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message - message
     * @param cause   - cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param cause - cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message            - message
     * @param cause              - cause
     * @param enableSuppression  - suppression enabled or not
     * @param writableStackTrace - whether the stack trace should be writable
     */
    protected ServiceException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
