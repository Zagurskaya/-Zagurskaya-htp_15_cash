package com.zagurskaya.cash.exception;

/**
 * Ошибка нарушения ограничений репозитория
 */
public class DaoConstraintViolationException extends Exception {

    /**
     * Constructor
     *
     * @param message - message
     * @param cause   - cause
     */
    public DaoConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
