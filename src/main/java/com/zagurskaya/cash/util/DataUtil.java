package com.zagurskaya.cash.util;

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
import java.util.Arrays;

public class DataUtil {
    private static final Logger logger = LogManager.getLogger(DataUtil.class);
    private static final String POST = "post";
    private static final String SAVE = "save";
    private static final String CANCEL = "cancel";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    public static User findUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String login = DataUtil.readCookie(request, AttributeConstant.LOGIN);
        String role = DataUtil.readCookie(request, AttributeConstant.ROLE);

        if (session == null || login == null || role == null) {
            return null;
        }
        Object oUser = session.getAttribute(AttributeConstant.USER);
        if (oUser == null) {
            return null;
        }
        User user = (User) oUser;
        if (!user.getLogin().equals(login) || !user.getRole().equals(role)) {
            return null;
        } else {
            return user;
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

    public static String readCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteCookie(HttpServletRequest request, String key) {
        Cookie readCookie = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(key)) {
                readCookie = cookie;
                break;
            }
        }
        readCookie.setMaxAge(0);
        DataUtil.setCookie(request, readCookie);
    }

    public static boolean isCreateUpdateDeleteOperation(HttpServletRequest req) {
        return req.getMethod().
                equalsIgnoreCase(POST);
    }

    public static boolean isSaveOperation(HttpServletRequest request) {
        return request.getParameter(SAVE) != null;
    }

    public static boolean isCancelOperation(HttpServletRequest request) {
        return request.getParameter(CANCEL) != null;
    }

    public static boolean isUpdateOperation(HttpServletRequest request) {
        return request.getParameter(UPDATE) != null;
    }

    public static boolean isDeleteOperation(HttpServletRequest request) {
        return request.getParameter(DELETE) != null;
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

    public static Long getLong(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return Long.parseLong(value);
    }
}
