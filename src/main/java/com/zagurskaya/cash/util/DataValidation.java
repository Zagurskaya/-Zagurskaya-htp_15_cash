package com.zagurskaya.cash.util;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Data validator
 */
public class DataValidation {
    private static final Logger logger = LogManager.getLogger(DataValidation.class);
    private static final String POST = "post";
    private static final String SAVE = "save";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String OPEN = "open";
    private static final String CLOSE = "close";
    private static final String ENTER = "enter";
    private static final String BALANCE = "balance";

    /**
     * Returns true if POST request.
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isCreateUpdateDeleteOperation(HttpServletRequest request) {
        return request.getMethod().
                equalsIgnoreCase(POST);
    }

    /**
     * Returns true if the action is "Save".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isSaveOperation(HttpServletRequest request) {
        return request.getParameter(SAVE) != null;
    }

    /**
     * Returns true if the action is "Update".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isUpdateOperation(HttpServletRequest request) {
        return request.getParameter(UPDATE) != null;
    }

    /**
     * Returns true if the action is "Delete".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isDeleteOperation(HttpServletRequest request) {
        return request.getParameter(DELETE) != null;
    }

    /**
     * Returns true if the action is "Open duties".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isOpenOperation(HttpServletRequest request) {
        return request.getParameter(OPEN) != null;
    }

    /**
     * Returns true if the action is "Close duties".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isCloseOperation(HttpServletRequest request) {
        return request.getParameter(CLOSE) != null;
    }
   /**
     * Returns true if the action is "continue operation".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isContinueOperation(HttpServletRequest request) {
        return request.getParameter(ENTER) != null;
    }   /**
     * Returns true if the action is "get balance".
     *
     * @param request - request
     * @return boolean
     */
    public static boolean isGetBalanceOperation(HttpServletRequest request) {
        return request.getParameter(BALANCE) != null;
    }

    /**
     * Checking the validity of the length of user fields
     *
     * @param request - request
     * @throws CommandException when the allowed number of characters in the field is exceeded
     */
    public static boolean isUserLengthFieldsValid(HttpServletRequest request) throws CommandException {
        if (request.getParameter(AttributeName.LOGIN) != null) {
            String username = ControllerDataUtil.getString(request, AttributeName.LOGIN);
            if (username.length() > FieldLength.LENGTH_USER_LOGIN) {
                logger.log(Level.ERROR, " Data length exceeds the allowed value (" + FieldLength.LENGTH_USER_LOGIN + " : " + ControllerDataUtil.convertHtmlSpecialChars(username) + ")");
                throw new CommandException("101 (" + FieldLength.LENGTH_USER_LOGIN + " : " + ControllerDataUtil.convertHtmlSpecialChars(username) + ")");
            }
        }
        if (request.getParameter(AttributeName.PASSWORD) != null) {
            String surname = ControllerDataUtil.getString(request, AttributeName.PASSWORD);
            if (surname.length() > FieldLength.LENGTH_USER_PASSWORD) {
                logger.log(Level.ERROR, " Data length exceeds the allowed value (" + FieldLength.LENGTH_USER_PASSWORD + " : " + ControllerDataUtil.convertHtmlSpecialChars(surname) + ")");
                throw new CommandException("101 (" + FieldLength.LENGTH_USER_PASSWORD + " : " + ControllerDataUtil.convertHtmlSpecialChars(surname));
            }
        }
        if (request.getParameter(AttributeName.FULL_MANE) != null) {
            String patronymic = ControllerDataUtil.getString(request, AttributeName.FULL_MANE);
            if (patronymic.length() > FieldLength.LENGTH_USER_FULL_NAME) {
                logger.log(Level.ERROR, " Data length exceeds the allowed value (" + FieldLength.LENGTH_USER_FULL_NAME + " : " + ControllerDataUtil.convertHtmlSpecialChars(patronymic) + ")");
                throw new CommandException("101 (" + FieldLength.LENGTH_USER_FULL_NAME + " : " + ControllerDataUtil.convertHtmlSpecialChars(patronymic));
            }
        }
        if (request.getParameter(AttributeName.ROLE) != null) {
            String userPosition = ControllerDataUtil.getString(request, AttributeName.ROLE);
            if (userPosition.length() > FieldLength.LENGTH_USER_ROLE) {
                logger.log(Level.ERROR, " Data length exceeds the allowed value (" + FieldLength.LENGTH_USER_ROLE + " : " + ControllerDataUtil.convertHtmlSpecialChars(userPosition) + ")");
                throw new CommandException("101  (" + FieldLength.LENGTH_USER_ROLE + " :" + ControllerDataUtil.convertHtmlSpecialChars(userPosition) + ")");
            }
        }
        return true;
    }
}
