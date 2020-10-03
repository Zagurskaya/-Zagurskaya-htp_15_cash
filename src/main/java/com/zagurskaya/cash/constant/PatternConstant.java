package com.zagurskaya.cash.constant;

public class PatternConstant {

    public static final String ALPHABET_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я]{1,}";
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN = "[\\w-]{1,}";
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN = "[\\w\\s-]{1,}";
    public static final String INPUT_ENCODING_CHARSET = "ISO-8859-1";

    private PatternConstant() {
    }
}
