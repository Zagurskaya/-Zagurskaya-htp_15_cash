package com.zagurskaya.cash.exception;

/**
 * Ошибка нарушения ограничений сервиса
 */
public class ServiceConstraintViolationException extends Exception {

    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public ServiceConstraintViolationException(String message) {
        super(message);
    }

    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause   - причина
     */
    public ServiceConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
