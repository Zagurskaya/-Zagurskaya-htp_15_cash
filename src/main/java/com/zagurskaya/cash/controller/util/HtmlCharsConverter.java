package com.zagurskaya.cash.controller.util;

/**
 * Html character converter
 */
public class HtmlCharsConverter {
    /**
     * Escaping symbols
     *
     * @param source - source text
     * @return character text
     */
    public static String convertHtmlSpecialChars(String source) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }
}
