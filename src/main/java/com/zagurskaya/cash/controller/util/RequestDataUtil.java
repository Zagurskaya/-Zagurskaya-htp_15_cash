package com.zagurskaya.cash.controller.util;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDataUtil {
    private static final Logger logger = LogManager.getLogger(RequestDataUtil.class);

    public static User findUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }
        Object user = session.getAttribute(AttributeName.USER);
        if (user == null) {
            return null;
        } else {
            return (User) user;
        }
    }

    public static void setCookie(HttpServletRequest request, Cookie cookie) {
        HttpServletResponse response =
                (HttpServletResponse) request.getAttribute(AttributeName.RESPONSE);
        response.addCookie(cookie);
    }

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
        RequestDataUtil.setCookie(request, readCookie);
    }


    public static String getString(HttpServletRequest request, String name, String pattern) throws CommandException {
        try {
            String value = new String(request.getParameter(name).getBytes(RegexPattern.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            if (value.matches(pattern))
                return HtmlCharsConverter.convertHtmlSpecialChars(value);
            else {
                logger.log(Level.ERROR, "Value  incorrect " + HtmlCharsConverter.convertHtmlSpecialChars(value));
                throw new CommandException("102 " + HtmlCharsConverter.convertHtmlSpecialChars(value));
            }
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new CommandException("102", e);
        }
    }

    public static String getString(HttpServletRequest request, String name) throws CommandException {
        try {
            String value = new String(request.getParameter(name).getBytes(RegexPattern.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new CommandException("102", e);
        }
    }

    public static long[] getLongArray(HttpServletRequest request, String name) {
        String[] value = request.getParameterValues(name);
        long[] longs = new long[value.length];
        for (int i = 0; i < value.length; i++) {
            longs[i] = Long.parseLong(value[i]);
        }
        return longs;
    }

    public static List<Long> getLongList(HttpServletRequest request, String name) {
        List<Long> arrList = new ArrayList<>();
        String[] value = request.getParameterValues(name);
        for (int i = 0; i < value.length; i++) {
            arrList.add(Long.parseLong(value[i]));
        }
        return arrList;
    }

    public static double[] getDoubleArray(HttpServletRequest request, String name) {
        String[] value = request.getParameterValues(name);
        double[] values = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            values[i] = Math.round(Double.parseDouble(value[i]) * 100.0) / 100.0;
        }
        return values;
    }

    public static List<Double> getDoubleList(HttpServletRequest request, String name) {
        List<Double> arrList = new ArrayList<>();
        String[] value = request.getParameterValues(name);
        for (int i = 0; i < value.length; i++) {
            arrList.add(Math.round(Double.parseDouble(value[i]) * 100.0) / 100.0);
        }
        return arrList;
    }

    public static Map<Long, Double> getMapLongDouble(HttpServletRequest request, String key, String value) {
        Map<Long, Double> map = new HashMap<>();
        String[] keys = request.getParameterValues(key);
        String[] values = request.getParameterValues(value);
        for (int i = 0; i < keys.length; i++) {
            map.put(Long.parseLong(keys[i]), Math.round(Double.parseDouble(values[i]) * 100.0) / 100.0);
        }
        return map;
    }
}
