package com.zagurskaya.cash.controller.util;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RequestDataUtil {
    private static final Logger logger = LogManager.getLogger(RequestDataUtil.class);

    /**
     * returns the user if he and the session exist
     *
     * @param request - request
     * @return user
     */
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

    /**
     * returns the Cookie for cookieName
     *
     * @param request    - request
     * @param cookieName -cookie name
     * @return cookie with name cookieName
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }

    /**
     * Getting a String value matching the pattern
     *
     * @param request - request
     * @param name    - field name
     * @param pattern - checked pattern
     * @return field value
     */
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

    /**
     * Getting a String value
     *
     * @param request - request
     * @param name    - field name
     * @return field value
     */
    public static String getString(HttpServletRequest request, String name) throws CommandException {
        try {
            String value = new String(request.getParameter(name).getBytes(RegexPattern.INPUT_ENCODING_CHARSET), StandardCharsets.UTF_8);
            return HtmlCharsConverter.convertHtmlSpecialChars(value);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new CommandException("102", e);
        }
    }

    /**
     * Getting a Map<Long, Double> value
     *
     * @param request - request
     * @param key     - attribute of key
     * @param value   - attribute of value
     * @return Map<Long, Double>
     */
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
