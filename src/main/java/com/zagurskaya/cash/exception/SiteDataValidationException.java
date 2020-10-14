package com.zagurskaya.cash.exception;

public class SiteDataValidationException extends Exception {
    /**
     * Конструктор
     */
    public SiteDataValidationException() {
        super();
    }

    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public SiteDataValidationException(String message) {
        super(message);
    }

    /**
     * Конструктор
     *
     * @param message - сообщение
     * @param cause   - причина
     */
    public SiteDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор
     *
     * @param cause - причина
     */
    public SiteDataValidationException(Throwable cause) {
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
    protected SiteDataValidationException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
