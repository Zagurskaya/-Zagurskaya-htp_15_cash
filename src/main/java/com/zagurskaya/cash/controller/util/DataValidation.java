package com.zagurskaya.cash.controller.util;

import com.zagurskaya.cash.exception.SiteDataValidationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Валидатор данных
 */
public class DataValidation {
    private static final Logger logger = LogManager.getLogger(DataValidation.class);
    private static final String POST = "post";
    private static final String SAVE = "save";
    private static final String CANCEL = "cancel";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String OPEN = "open";
    private static final String CLOSE = "close";

    /**
     * Возвращает true, если POST запрос.
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isCreateUpdateDeleteOperation(HttpServletRequest request) {
        return request.getMethod().
                equalsIgnoreCase(POST);
    }

    /**
     * Возвращает true, если действие "Сохранить".
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isSaveOperation(HttpServletRequest request) {
        return request.getParameter(SAVE) != null;
    }

    /**
     * Возвращает true, если действие "Отмена".
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isCancelOperation(HttpServletRequest request) {
        return request.getParameter(CANCEL) != null;
    }

    /**
     * Возвращает true, если действие "Обновление".
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isUpdateOperation(HttpServletRequest request) {
        return request.getParameter(UPDATE) != null;
    }

    /**
     * Возвращает true, если действие "Удаление".
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isDeleteOperation(HttpServletRequest request) {
        return request.getParameter(DELETE) != null;
    }

    /**
     * Возвращает true, если действие "Открыть смену".
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isOpenOperation(HttpServletRequest request) {
        return request.getParameter(OPEN) != null;
    }

    /**
     * Возвращает true, если действие "Закрыть смену".
     *
     * @param request - запрос
     * @return boolean
     */
    public static boolean isCloseOperation(HttpServletRequest request) {
        return request.getParameter(CLOSE) != null;
    }

    /**
     * Проверка валидности длины полей пользователя
     *
     * @param request - запрос
     * @throws SiteDataValidationException при превышении допустимого количества символов в поле
     */
    public static boolean isUserLengthFieldsValid(HttpServletRequest request) throws SiteDataValidationException {
        if (request.getParameter("login") != null) {
            String username = RequestDataUtil.getString(request, "login");
            if (username.length() > FieldLength.LENGTH_USER_LOGIN) {
                logger.log(Level.ERROR, " Длина данных превышает допустимое значение (" + FieldLength.LENGTH_USER_LOGIN + " : " + HtmlCharsConverter.convertHtmlSpecialChars(username) + ")");
                throw new SiteDataValidationException("101 (" + FieldLength.LENGTH_USER_LOGIN + " : " + HtmlCharsConverter.convertHtmlSpecialChars(username) + ")");
            }
        }
        if (request.getParameter("password") != null) {
            String surname = RequestDataUtil.getString(request, "password");
            if (surname.length() > FieldLength.LENGTH_USER_PASSWORD) {
                logger.log(Level.ERROR, " Длина данных превышает допустимое значение (" + FieldLength.LENGTH_USER_PASSWORD + " : " + HtmlCharsConverter.convertHtmlSpecialChars(surname) + ")");
                throw new SiteDataValidationException("101 (" + FieldLength.LENGTH_USER_PASSWORD + " : " + HtmlCharsConverter.convertHtmlSpecialChars(surname));
            }
        }
        if (request.getParameter("fullname") != null) {
            String patronymic = RequestDataUtil.getString(request, "fullname");
            if (patronymic.length() > FieldLength.LENGTH_USER_FULL_NAME) {
                logger.log(Level.ERROR, " Длина данных превышает допустимое значение (" + FieldLength.LENGTH_USER_FULL_NAME + " : " + HtmlCharsConverter.convertHtmlSpecialChars(patronymic) + ")");
                throw new SiteDataValidationException("101 (" + FieldLength.LENGTH_USER_FULL_NAME + " : " + HtmlCharsConverter.convertHtmlSpecialChars(patronymic));
            }
        }
        if (request.getParameter("role") != null) {
            String userPosition = RequestDataUtil.getString(request, "role");
            if (userPosition.length() > FieldLength.LENGTH_USER_ROLE) {
                logger.log(Level.ERROR, " Длина данных превышает допустимое значение (" + FieldLength.LENGTH_USER_ROLE + " : " + HtmlCharsConverter.convertHtmlSpecialChars(userPosition) + ")");
                throw new SiteDataValidationException("101  (" + FieldLength.LENGTH_USER_ROLE + " :" + HtmlCharsConverter.convertHtmlSpecialChars(userPosition) + ")");
            }
        }
        return true;
    }
}
