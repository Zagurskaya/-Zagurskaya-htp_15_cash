package com.zagurskaya.cash.exception;

/**
 * Ошибка нарушения ограничений репозитория
 */
public class RepositoryConstraintViolationException extends Exception {

    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause   - причина
     */
    public RepositoryConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
