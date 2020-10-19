package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.RegexPattern;
import com.zagurskaya.cash.controller.util.UserExtractor;
import com.zagurskaya.cash.controller.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Создать пользователя".
 */
public class CreateUserCommand extends AbstractСommand {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);
    private static final String LOGIN = "login";
    private static final String FULL_NAME = "fullname";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    /**
     * Конструктор
     *
     * @param path - путь
     */
    public CreateUserCommand(String path) {
        super(path);
    }

    @Override
    public ActionType execute(HttpServletRequest request) throws CommandException, ServiceConstraintViolationException {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("message");
        session.removeAttribute("error");

        ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.CREATEUSER);
        if (actionType == ActionType.CREATEUSER) {
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {

                if (DataValidation.isCancelOperation(request)) {
                    return ActionType.EDITUSERS;

                } else if (DataValidation.isSaveOperation(request)) {
                    UserExtractor userExtractor = new UserExtractor();
                    User createdUser = userExtractor.userNotCheckedFieldsToUser(request);
                    request.setAttribute(AttributeName.USER, createdUser);

                    String login = RequestDataUtil.getString(request, LOGIN, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
                    String fullName = RequestDataUtil.getString(request, FULL_NAME, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
                    String password = RequestDataUtil.getString(request, PASSWORD, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
                    String role = RequestDataUtil.getString(request, ROLE, RegexPattern.ALPHABET_VALIDATE_PATTERN);

                    if (DataValidation.isUserLengthFieldsValid(request)) {
                        User newUser = new User
                                .Builder()
                                .addLogin(login)
                                .addPassword(password)
                                .addFullName(fullName)
                                .addRole(role)
                                .build();
                        try {
                            userService.create(newUser);
                            logger.log(Level.INFO, "Add new user " + newUser.getLogin());
                            session.setAttribute(AttributeName.MESSAGE, "107 " + newUser.getLogin());
                            return ActionType.EDITUSERS;
                        } catch (ServiceException e) {
                            session.setAttribute(AttributeName.ERROR, e);
                            logger.log(Level.ERROR, e);
                            return ActionType.ERROR;
                        }
                    }
                }
            }

            final User newUser = new User();
            request.setAttribute(AttributeName.USER, newUser);
            return ActionType.CREATEUSER;
        } else {
            return actionType;
        }
    }
}
