package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.RegexPattern;
import com.zagurskaya.cash.controller.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Регистрация".
 */
public class LoginСommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(LoginСommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private UserService userService = new UserServiceImpl();

    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public LoginСommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request) throws CommandException {
        final HttpSession session = request.getSession(false);

        if (DataValidation.isCreateUpdateDeleteOperation(request)) {
            String login = RequestDataUtil.getString(request, LOGIN, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
            String password = RequestDataUtil.getString(request, PASSWORD, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
            User user;
            try {
                user = userService.getUserByLoginAndValidPassword(login, password);
                if (user != null) {
                    session.setAttribute(AttributeName.USER, user);
                    return ActionType.PROFILE;
                } else {
                    logger.log(Level.ERROR, "Value incorrect");
                    throw new CommandException("102 ");
                }
            } catch (ServiceException e) {
                session.setAttribute(AttributeName.ERROR, e);
                logger.log(Level.ERROR, e);
                return ActionType.ERROR;
            }
        }
        return ActionType.LOGIN;
    }
}
