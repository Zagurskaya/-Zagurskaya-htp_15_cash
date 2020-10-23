package com.zagurskaya.cash.exception;

/**
 * Ошибка Dao
 */
public class DaoException extends Exception {
    /**
     * Constructor
     */
    public DaoException() {
        super();
    }
    /**
     * Constructor
     *
     * @param message - message
     */
    public DaoException(String message) {
        super(message);
    }
    /**
     * Constructor
     *
     * @param message - message
     * @param cause - cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructor
     *
     * @param cause - cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
    /**
     * Constructor
     *
     * @param message - message
     * @param cause - cause
     * @param enableSuppression - suppression enabled or not
     * @param writableStackTrace - whether the stack trace should be writable
     */
    protected DaoException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
