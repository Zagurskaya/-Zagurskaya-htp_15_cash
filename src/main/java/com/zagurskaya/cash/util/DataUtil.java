package com.zagurskaya.cash.util;

import com.zagurskaya.cash.controller.util.HtmlCharsConverter;
import com.zagurskaya.cash.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataUtil {
    private static final Logger logger = LogManager.getLogger(DataUtil.class);

    public static String getString(String value, String pattern) throws CommandException {
        if (value.matches(pattern))
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        else {
            logger.log(Level.ERROR, "Value  incorrect" + HtmlCharsConverter.convertHtmlSpecialChars(value));
            throw new CommandException("102" + HtmlCharsConverter.convertHtmlSpecialChars(value));
        }
    }

    public static Long getLong(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Long.parseLong(value);
    }

    public static String getFormattedLocalDateStartDateTime(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000"));
    }

    public static String getFormattedLocalDateOnlyDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static Double round(Double sum) {
        return Math.round(sum * 100.0) / 100.0;
    }
}
