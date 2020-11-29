package com.zagurskaya.cash.exception;

/**
 * Negative balance exception
 */
public class NegativeBalanceException extends Exception {

    /**
     * Constructor
     *
     * @param message - message
     */
    public NegativeBalanceException(String message) {
        super(message);
    }
}
