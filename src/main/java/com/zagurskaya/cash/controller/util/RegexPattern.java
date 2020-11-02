package com.zagurskaya.cash.controller.util;

/**
 * Pattern
 */
public class RegexPattern {
    /**
     * String validation pattern: not empty, upper and lower case alphabet
     */
    public static final String ALPHABET_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я]{1,}";
    /**
     * String validation pattern: not empty, upper and lower case alphabet, numbers 0 through 9, "-" and "_"
     */
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я0-9_-]{1,}";
    /**
     * String validation pattern: not empty, upper and lower case alphabet, numbers from 0 to 9, space, "-" and "_"
     */
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я0-9\\s_-]{1,}";
    /**
     * Encoding
     */
    public static final String INPUT_ENCODING_CHARSET = "ISO-8859-1";

    private RegexPattern() {
    }
}
