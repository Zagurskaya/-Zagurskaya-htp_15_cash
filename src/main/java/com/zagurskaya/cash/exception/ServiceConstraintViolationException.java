package com.zagurskaya.cash.exception;

/**
 * Ошибка нарушения ограничений сервиса
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
