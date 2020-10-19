package com.zagurskaya.cash.util;

import com.sun.javafx.collections.MappingChange;
import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.constant.PatternConstant;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final Logger logger = LogManager.getLogger(DataUtil.class);

    public static User findUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }
        Object user = session.getAttribute(AttributeConstant.USER);
        if (user == null) {
            return null;
        } else {
            return (User) user;
        }
    }
//    public static String getHash(User user) {
//        //хеш можно получить проще, на занятии
//        //я что-то перемудрил.
//        String key = user.getLogin() + user.getPassword() + "это как бы соль";
//        return DigestUtils.md5Hex(key);
//    }

    public static void setCookie(HttpServletRequest request, Cookie cookie) {
        HttpServletResponse response =
                (HttpServletResponse) request.getAttribute(AttributeConstant.RESPONSE);
        response.addCookie(cookie);
    }

//    public static String readCookie(HttpServletRequest request, String key) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(key)) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
//    }

    public static void deleteCookie(HttpServletRequest request, String key) {
        Cookie readCookie = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(key)) {
                readCookie = cookie;
                break;
            }
        }
        if (readCookie != null) {
            readCookie.setMaxAge(0);
        }
        DataUtil.setCookie(request, readCookie);
    }


    public static String getString(HttpServletRequest req, String name, String pattern) throws SiteDataValidationException {
        try {
            String value = new String(req.getParameter(name).getBytes(PatternConstant.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            if (value.matches(pattern))
                return HtmlCharsConverter.convertHtmlSpecialChars(value);
            else {
                logger.log(Level.ERROR, "Value  incorrect " + HtmlCharsConverter.convertHtmlSpecialChars(value));
                throw new SiteDataValidationException("102 " + HtmlCharsConverter.convertHtmlSpecialChars(value));
            }
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new SiteDataValidationException("102", e);
        }
    }

    public static String getString(HttpServletRequest request, String name) throws SiteDataValidationException {
        try {
            String value = new String(request.getParameter(name).getBytes(PatternConstant.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new SiteDataValidationException("102", e);
        }
    }

    public static String getString(String value, String pattern) throws SiteDataValidationException {
        if (value.matches(pattern))
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        else {
            logger.log(Level.ERROR, "Value  incorrect" + HtmlCharsConverter.convertHtmlSpecialChars(value));
            throw new SiteDataValidationException("102" + HtmlCharsConverter.convertHtmlSpecialChars(value));
        }
    }

    public static Long getLong(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Long.parseLong(value);
    }

    public static long[] getLongArray(HttpServletRequest req, String name) {
        String[] value = req.getParameterValues(name);
        long[] longs = new long[value.length];
        for (int i = 0; i < value.length; i++) {
            longs[i] = Long.parseLong(value[i]);
        }
        return longs;
    }

    public static List<Long> getLongList(HttpServletRequest req, String name) {
        List<Long> arrList = new ArrayList<>();
        String[] value = req.getParameterValues(name);
        for (int i = 0; i < value.length; i++) {
            arrList.add(Long.parseLong(value[i]));
        }
        return arrList;
    }

    public static double[] getDoubleArray(HttpServletRequest req, String name) {
        String[] value = req.getParameterValues(name);
        double[] values = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            values[i] = Math.round(Double.parseDouble(value[i]) * 100.0) / 100.0;
        }
        return values;
    }

    public static List<Double> getDoubleList(HttpServletRequest req, String name) {
        List<Double> arrList = new ArrayList<>();
        String[] value = req.getParameterValues(name);
        for (int i = 0; i < value.length; i++) {
            arrList.add(Math.round(Double.parseDouble(value[i]) * 100.0) / 100.0);
        }
        return arrList;
    }

    public static Map<Long, Double> getMapLongDouble(HttpServletRequest req, String key, String value) {
        Map<Long, Double> map = new HashMap<>();
        String[] keys = req.getParameterValues(key);
        String[] values = req.getParameterValues(value);
        for (int i = 0; i < keys.length; i++) {
            map.put(Long.parseLong(keys[i]), Math.round(Double.parseDouble(values[i]) * 100.0) / 100.0);
        }
        return map;
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
