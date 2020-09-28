package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.util.PatternConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginСommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(LoginСommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private UserService userService = new UserServiceImpl();

    public LoginСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) throws SiteDataValidationException {
        final HttpSession session = request.getSession(false);

        if (DataUtil.isCreateUpdateDeleteOperation(request)) {
            String login = DataUtil.getString(request, LOGIN, PatternConstant.LOGIN_VALIDATE_PATTERN);
            String password = DataUtil.getString(request, PASSWORD, PatternConstant.PASSWORD_VALIDATE_PATTERN);
            User user;
            try {
                user = userService.getUserByLoginAndValidPassword(login, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    return Action.PROFILE;
                }
            } catch (ServiceException e) {
                session.setAttribute("error", e);
                logger.log(Level.ERROR, e);
                return Action.ERROR;
            }
        }
        return Action.LOGIN;
    }
}
