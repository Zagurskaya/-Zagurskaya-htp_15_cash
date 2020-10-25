package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractCommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;
import com.zagurskaya.cash.entity.User;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The action is "Create user".
 */
public class CreateUserCommand extends AbstractCommand {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);
    private static final String LOGIN = "login";
    private static final String FULL_NAME = "fullname";
    private static final String PASSWORD = "password";
    private static final String REITERATE_PASSWORD = "reiteratepassword";
    private static final String ROLE = "role";

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public CreateUserCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession(false);
        session.removeAttribute(AttributeName.MESSAGE);
        session.removeAttribute(AttributeName.ERROR);
        try {
            ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.CREATEUSER);
            if (actionType == ActionType.CREATEUSER) {
                if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                    if (DataValidation.isCancelOperation(request)) {
                        return ActionType.EDITUSERS;

                    } else if (DataValidation.isSaveOperation(request)) {
                        UserExtractor userExtractor = new UserExtractor();
                        User createdUser = userExtractor.userNotCheckedFieldsToUser(request);
                        request.setAttribute(AttributeName.USER, createdUser);

                        String login = ControllerDataUtil.getString(request, LOGIN, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
                        String fullName = ControllerDataUtil.getString(request, FULL_NAME, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
                        String password = ControllerDataUtil.getString(request, PASSWORD, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
                        String reiteratePassword = ControllerDataUtil.getString(request, REITERATE_PASSWORD, RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
                        String role = ControllerDataUtil.getString(request, ROLE, RegexPattern.ALPHABET_VALIDATE_PATTERN);

                        if (DataValidation.isUserLengthFieldsValid(request) && password.equals(reiteratePassword)) {
                            User newUser = new User.Builder()
                                    .addLogin(login)
                                    .addFullName(fullName)
                                    .addRole(role)
                                    .build();

                            userService.create(newUser, password);
                            logger.log(Level.INFO, "Add new user " + newUser.getLogin());
                            session.setAttribute(AttributeName.MESSAGE, "107 " + newUser.getLogin());
                            return ActionType.EDITUSERS;
                        }
                    }
                }

                final User newUser = new User();
                request.setAttribute(AttributeName.USER, newUser);
                return ActionType.CREATEUSER;
            } else {
                return actionType;
            }
        } catch (ServiceException e) {
            session.setAttribute(AttributeName.ERROR, e);
            logger.log(Level.ERROR, e);
            return ActionType.ERROR;
        }
    }
}
