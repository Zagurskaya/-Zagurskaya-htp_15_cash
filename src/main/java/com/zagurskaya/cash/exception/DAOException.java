package com.zagurskaya.cash.exception;

/**
 * Ошибка Dao
 */
public class DAOException extends Exception {
    /**
     * Конструктор
     */
    public DAOException() {
        super();
    }
    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public DAOException(String message) {
        super(message);
    }
    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause - причина
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Конструктор
     *
     * @param cause - причина
     */
    public DAOException(Throwable cause) {
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
    protected DAOException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
