package com.zagurskaya.cash.util;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class DataUtil {
    private static final Logger logger = LogManager.getLogger(DataUtil.class);

    public static User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null)
            return null;
        Object oUser = session.getAttribute("user");
        if (oUser == null) {
            return null;
        } else {
            return (User) oUser;
        }
    }

    public static boolean isCreateUpdateDeleteOperation(HttpServletRequest req) {
        return req.getMethod().
                equalsIgnoreCase("post");
    }

    public static boolean isSaveOperation(HttpServletRequest request) {
        return request.getParameter("save") != null;
    }

    public static boolean isCancelOperation(HttpServletRequest request) {
        return request.getParameter("cancel") != null;
    }

    public static boolean isUpdateOperation(HttpServletRequest request) {
        return request.getParameter("update") != null;
    }

    public static boolean isDeleteOperation(HttpServletRequest request) {
        return request.getParameter("delete") != null;
    }

    public static String getString(HttpServletRequest req, String name, String pattern) throws SiteDataValidationException {
        try {
            String value = new String(req.getParameter(name).getBytes(PatternConstant.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            if (value.matches(pattern))
                return HtmlCharsConverter.convertHtmlSpecialChars(value);
            else {
                logger.log(Level.ERROR, "Value " + HtmlCharsConverter.convertHtmlSpecialChars(value) + " incorrect");
                throw new SiteDataValidationException("Value " + HtmlCharsConverter.convertHtmlSpecialChars(value) + " incorrect");
            }
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new SiteDataValidationException("Value incorrect", e);
        }
    }

    public static String getString(HttpServletRequest request, String name) throws SiteDataValidationException {
        try {
            String value = new String(request.getParameter(name).getBytes(PatternConstant.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new SiteDataValidationException("Value incorrect", e);
        }
    }

    public static String getString(String value, String pattern) throws SiteDataValidationException {
        if (value.matches(pattern))
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        else {
            logger.log(Level.ERROR, "Value " + HtmlCharsConverter.convertHtmlSpecialChars(value) + " incorrect");
            throw new SiteDataValidationException("Value " + HtmlCharsConverter.convertHtmlSpecialChars(value) + " incorrect");
        }
    }

    public static int getInt(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Integer.parseInt(value);
    }

    public static Long getLong(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Long.parseLong(value);
    }

    public static double getDouble(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Double.parseDouble(value);
    }

    public static long[] getLongArray(HttpServletRequest req, String name) {
        String[] value = req.getParameterValues(name);
        long[] longs = new long[value.length];
        for (int i = 0; i < value.length; i++) {
            longs[i] = Long.parseLong(value[i]);
        }
        return longs;
    }

    public static double[] getDoubleArray(HttpServletRequest req, String name) {
        String[] value = req.getParameterValues(name);
        double[] values = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            values[i] = Math.round(Double.parseDouble(value[i]) * 100.0) / 100.0;
        }
        return values;
    }

    public static String[] getStringArray(HttpServletRequest req, String name) {
        String[] value = req.getParameterValues(name);
        return value;
    }
}
