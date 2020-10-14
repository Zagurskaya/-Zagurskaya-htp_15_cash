package com.zagurskaya.cash.exception;

/**
 * Пула соединений с БД
 */
public class ConnectionPoolException extends Exception {
    /**
     * Конструктор
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause   - причина
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор
     *
     * @param cause - причина
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    /**
     * Конструктор
     *
     * @param message            - сообщение
     * @param cause              - причина
     * @param enableSuppression  - включено подавление или нет
     * @param writableStackTrace - должна ли трассировка стека быть доступной для записи
     */
    protected ConnectionPoolException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
