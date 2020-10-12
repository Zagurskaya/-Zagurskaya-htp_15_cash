package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.constant.PatternConstant;
import com.zagurskaya.cash.validation.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginСommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(LoginСommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private UserService userService = new UserServiceImpl();

    public LoginСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) throws SiteDataValidationException {
        final HttpSession session = request.getSession(false);

        if (DataValidation.isCreateUpdateDeleteOperation(request)) {
            String login = DataUtil.getString(request, LOGIN, PatternConstant.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
            String password = DataUtil.getString(request, PASSWORD, PatternConstant.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
            User user;
            try {
                user = userService.getUserByLoginAndValidPassword(login, password);
                if (user != null) {
                    session.setAttribute(AttributeConstant.USER, user);
                    Cookie loginCookie = new Cookie(LOGIN, user.getLogin());
                    DataUtil.setCookie(request, loginCookie);
                    Cookie roleCookie = new Cookie(ROLE, user.getRole().getValue());
                    DataUtil.setCookie(request, roleCookie);
                    return Action.PROFILE;
                } else {
                    logger.log(Level.ERROR, "Value incorrect");
                    throw new SiteDataValidationException("102 ");
                }
            } catch (ServiceException e) {
                session.setAttribute(AttributeConstant.ERROR, e);
                logger.log(Level.ERROR, e);
                return Action.ERROR;
            }
        }
        return Action.LOGIN;
    }
}
