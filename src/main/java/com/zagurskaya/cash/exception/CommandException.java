package com.zagurskaya.cash.exception;

public class CommandException extends Exception {
    /**
     * Constructor
     */
    public CommandException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message - message
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message - message
     * @param cause   - cause
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param cause - cause
     */
    public CommandException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message            - message
     * @param cause              - cause
     * @param enableSuppression  - suppression enabled or not
     * @param writableStackTrace - whether the stack trace should be writable
     */
    protected CommandException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
