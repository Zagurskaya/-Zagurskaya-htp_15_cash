package com.zagurskaya.cash.validation;

import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.constant.FieldsLengthConstant;
import com.zagurskaya.cash.util.HtmlCharsConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DataValidation {
    private static final Logger logger = LogManager.getLogger(DataValidation.class);
    private static final String POST = "post";
    private static final String SAVE = "save";
    private static final String CANCEL = "cancel";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

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

    public static boolean isUserLengthFieldsValid(HttpServletRequest request) throws SiteDataValidationException {

        String username = DataUtil.getString(request, "login");
        if (username.length() > FieldsLengthConstant.LENGTH_USER_LOGIN) {
            throw new SiteDataValidationException(" Длина данных превышает допустимое значение (" + FieldsLengthConstant.LENGTH_USER_LOGIN + " символов):" + HtmlCharsConverter.convertHtmlSpecialChars(username));
        }
        String surname = DataUtil.getString(request, "password");
        if (surname.length() > FieldsLengthConstant.LENGTH_USER_PASSWORD) {
            throw new SiteDataValidationException(" Длина данных превышает допустимое значение (" + FieldsLengthConstant.LENGTH_USER_PASSWORD + " символов):" + HtmlCharsConverter.convertHtmlSpecialChars(surname));
        }
        String patronymic = DataUtil.getString(request, "fullname");
        if (patronymic.length() > FieldsLengthConstant.LENGTH_USER_FULL_NAME) {
            throw new SiteDataValidationException(" Длина данных превышает допустимое значение (" + FieldsLengthConstant.LENGTH_USER_FULL_NAME + " символов):" + HtmlCharsConverter.convertHtmlSpecialChars(patronymic));
        }
        String userPosition = DataUtil.getString(request, "role");
        if (userPosition.length() > FieldsLengthConstant.LENGTH_USER_ROLE) {
            throw new SiteDataValidationException(" Длина данных превышает допустимое значение (" + FieldsLengthConstant.LENGTH_USER_ROLE + " символов):" + HtmlCharsConverter.convertHtmlSpecialChars(userPosition));
        }
        return true;
    }
}
