package com.zagurskaya.cash.util;

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
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerDataUtil {
    private static final Logger logger = LogManager.getLogger(ControllerDataUtil.class);

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
     * Escaping symbols
     *
     * @param source - source text
     * @return character text
     */
    static String convertHtmlSpecialChars(String source) {

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
                return ControllerDataUtil.convertHtmlSpecialChars(value);
            else {
                logger.log(Level.ERROR, "Value  incorrect " + ControllerDataUtil.convertHtmlSpecialChars(value));
                throw new CommandException("102 " + ControllerDataUtil.convertHtmlSpecialChars(value));
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
            return ControllerDataUtil.convertHtmlSpecialChars(value);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Value incorrect", e);
            throw new CommandException("102", e);
        }
    }

    /**
     * Getting a Map<Long, BigDecimal> value
     *
     * @param request - request
     * @param key     - attribute of key
     * @param value   - attribute of value
     * @return Map<Long, BigDecimal>
     */
    public static Map<Long, BigDecimal> getMapLongBigDecimal(HttpServletRequest request, String key, String value) {
        Map<Long, BigDecimal> map = new LinkedHashMap<>();
        String[] keys = request.getParameterValues(key);
        String[] values = request.getParameterValues(value);
        for (int i = 0; i < keys.length; i++) {
            if (!"".equals(values[i])) {
                map.put(Long.parseLong(keys[i]), DataUtil.round(new BigDecimal(values[i])));
            }
        }
        return map;
    }

    /**
     * Getting a String value
     *
     * @param request - request
     * @param name    - field name
     * @return field value
     */
    public static Long getLong(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return Long.parseLong(value);
    }

    /**
     * Remove from the session attribute "Message"
     */
    public static void removeAttributeMessage(HttpServletRequest request) {
        request.getSession(false).removeAttribute(AttributeName.MESSAGE);
    }

    /**
     * Remove from the session attribute "Error"
     */
    public static void removeAttributeError(HttpServletRequest request) {
        request.getSession(false).removeAttribute(AttributeName.ERROR);
    }

    /**
     * Remove from the session attribute "User"
     */
    public static void removeAttributeUser(HttpServletRequest request) {
        request.getSession(false).removeAttribute(AttributeName.USER);
    }
}
