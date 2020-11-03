package com.zagurskaya.cash.util;

import com.zagurskaya.cash.controller.util.ControllerDataUtil;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataUtil {
    private static final Logger logger = LogManager.getLogger(DataUtil.class);

    /**
     * Get a string matching the pattern
     *
     * @param value   - matching value
     * @param pattern - matching pattern
     * @return true on successful update
     * @throws CommandException validation error
     */
    public static String getString(String value, String pattern) throws CommandException {
        if (value.matches(pattern))
            return ControllerDataUtil.convertHtmlSpecialChars(value);
        else {
            logger.log(Level.ERROR, "Value  incorrect" + ControllerDataUtil.convertHtmlSpecialChars(value));
            throw new CommandException("102" + ControllerDataUtil.convertHtmlSpecialChars(value));
        }
    }

    /**
     * Get long type from request parameter
     *
     * @param req  - request
     * @param name - parameter name
     * @return long type value
     * @throws CommandException validation error
     */
    public static Long getLong(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Long.parseLong(value);
    }

    /**
     * Format date : yyyy-MM-dd 00:00:00.000
     *
     * @param localDate - date
     * @return string type date
     * @throws CommandException validation error
     */
    public static String getFormattedLocalDateStartDateTime(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000"));
    }

    /**
     * Format date :  yyyy-MM-dd
     *
     * @param localDate - date
     * @return string type date
     */
    public static String getFormattedLocalDateOnlyDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Format date :  yyyyddMMHHmmss
     *
     * @param dateTime - date and time
     * @return string type date
     */
    public static String getFormattedTimestamp(Timestamp dateTime) {
        return new SimpleDateFormat("yyyyddMMHHmmss").format(dateTime);
    }

    /**
     * Format date :  yyyy.dd.MM HH:mm:ss
     *
     * @param dateTime - date and time
     * @return string type date
     */
    public static String getFormattedCheck(Timestamp dateTime) {
        return new SimpleDateFormat("yyyy.dd.MM HH:mm:ss").format(dateTime);
    }

    /**
     * Round sum
     *
     * @param sum - sum
     * @return round sum
     */
    public static Double round(Double sum) {
        return Math.round(sum * 100.0) / 100.0;
    }
}
