package com.zagurskaya.cash.exception;

/**
 * Ошибка Dao
 */
public class DaoException extends Exception {
    /**
     * Конструктор
     */
    public DaoException() {
        super();
    }
    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public DaoException(String message) {
        super(message);
    }
    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause - причина
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Конструктор
     *
     * @param cause - причина
     */
    public DaoException(Throwable cause) {
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
    protected DaoException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
