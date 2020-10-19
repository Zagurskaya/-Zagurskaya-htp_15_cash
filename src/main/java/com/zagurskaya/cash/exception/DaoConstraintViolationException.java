package com.zagurskaya.cash.exception;

/**
 * Ошибка нарушения ограничений репозитория
 */
public class DaoConstraintViolationException extends Exception {

    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause   - причина
     */
    public DaoConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
