package com.zagurskaya.cash.exception;
/**
 * Ошибка сервиса
 */
public class ServiceException extends Exception {
    /**
     * Конструктор
     */
    public ServiceException() {
        super();
    }
    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause   - причина
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Конструктор
     *
     * @param cause - причина
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause - причина
     * @param enableSuppression - включено подавление или нет
     * @param writableStackTrace - должна ли трассировка стека быть доступной для записи
     */
    protected ServiceException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
