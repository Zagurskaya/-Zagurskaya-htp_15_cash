package com.zagurskaya.cash.util;

public class PatternConstant {

    public static final String LOGIN_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я]{1,}";
    public static final String PASSWORD_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я]{1,}";
    public static final String ROLE_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я]{1,}";
    public static final String NUMBER_VALIDATE_PATTERN = "[0-9]{1,}";
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я0-9_\\s-]{1,}";
    public static final String INPUT_ENCODING_CHARSET = "ISO-8859-1";

    private PatternConstant() {
    }
}
