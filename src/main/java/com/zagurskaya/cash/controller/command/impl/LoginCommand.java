package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The action is "Registration".
 */
public class LoginCommand implements Command {
    private String directoryPath;
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private UserService userService = new UserServiceImpl();

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public LoginCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession(false);
        try {

            if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                String login = ControllerDataUtil.getString(request, LOGIN, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
                String password = ControllerDataUtil.getString(request, PASSWORD, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
                User user;
                user = userService.findUserByLoginAndValidPassword(login, password);
                if (user != null) {
                    session.setAttribute(AttributeName.USER, user);
                    return ActionType.PROFILE;
                } else {
                    logger.log(Level.ERROR, "Value incorrect");
                    session.setAttribute(AttributeName.ERROR, "102 ");
                    return ActionType.LOGIN;
                }
            }
            return ActionType.LOGIN;
        } catch (ServiceException e) {
            session.setAttribute(AttributeName.ERROR, e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
