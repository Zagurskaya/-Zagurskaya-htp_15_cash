package com.zagurskaya.cash.constant;

/**
 * Шаблоны
 */
public class PatternConstant {
    /**
     * Шаблон проверки строки: не пустого значения, алфавит в верхнем и нижнем регистре
     */
    public static final String ALPHABET_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я]{1,}";
    /**
     * Шаблон проверки строки: не пустого значения, алфавит в верхнем и нижнем регистре, цифры от 0 до 9, символ "-" и "_"
     */
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я0-9_-]{1,}";
    /**
     * Шаблон проверки строки: не пустого значения, алфавит в верхнем и нижнем регистре, цифры от 0 до 9, пробел, символ "-" и "_"
     */
    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN = "[a-zA-Zа-яА-Я0-9\\s_-]{1,}";
    /**
     * Кодировка
     */
    public static final String INPUT_ENCODING_CHARSET = "ISO-8859-1";

    private PatternConstant() {
    }
}
