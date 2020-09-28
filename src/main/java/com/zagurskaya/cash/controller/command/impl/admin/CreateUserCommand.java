package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.util.PatternConstant;
import com.zagurskaya.cash.util.UserExtractor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateUserCommand extends AbstractСommand {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);

    public CreateUserCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) throws SiteDataValidationException, ServiceConstraintViolationException {
        final HttpSession session = request.getSession(false);

        if (DataUtil.isCreateUpdateDeleteOperation(request)) {

            if (DataUtil.isCancelOperation(request)) {
                return Action.EDITUSERS;

            } else if (DataUtil.isSaveOperation(request)) {

                User createdUser = new User();
                UserExtractor.setUserNotCheckedFieldsToUser(request, createdUser);
                request.setAttribute("user", createdUser);

                String login = DataUtil.getString(request, "login", PatternConstant.LOGIN_VALIDATE_PATTERN);
                String password = DataUtil.getString(request, "password", PatternConstant.PASSWORD_VALIDATE_PATTERN);
                String role = DataUtil.getString(request, "role", PatternConstant.ROLE_VALIDATE_PATTERN);

                User newUser = new User();
                newUser.setLogin(login);
                newUser.setPassword(password);
                newUser.setRole(role);
                try {
                    userService.create(newUser);
                    return Action.EDITUSERS;
                } catch (ServiceException e) {
                    session.setAttribute("error", e);
                    logger.log(Level.ERROR, e);
                    return Action.ERROR;
                }
            }
        }
        final User user = new User();
        request.setAttribute("user", user);
        return Action.CREATEUSER;
    }
}
