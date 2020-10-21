package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.controller.util.RegexPattern;
import com.zagurskaya.cash.controller.util.UserExtractor;
import com.zagurskaya.cash.controller.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Изменить пользователя".
 */
public class UpdateUserCommand extends AbstractСommand {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);

    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public UpdateUserCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request) throws CommandException {
        final HttpSession session = request.getSession(false);
        final Long id = (Long) session.getAttribute(AttributeName.ID);
        if (id == null) return ActionType.INDEX;
        session.removeAttribute("message");
        session.removeAttribute("error");

        ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.EDITUSERS);
        if (actionType == ActionType.EDITUSERS) {


            if (DataValidation.isCreateUpdateDeleteOperation(request)) {

                if (DataValidation.isCancelOperation(request)) {
                    return ActionType.EDITUSERS;

                } else if (DataValidation.isSaveOperation(request)) {
                    UserExtractor userExtractor = new UserExtractor();
                    User updatedUser = userExtractor.updateUserNotCheckedFieldsToUser(request);
                    request.setAttribute(AttributeName.USER, updatedUser);

                    String login = DataUtil.getString(updatedUser.getLogin(), RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_VALIDATE_PATTERN);
                    String fullName = DataUtil.getString(updatedUser.getFullName(), RegexPattern.ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN);
                    String role = DataUtil.getString(updatedUser.getRole().getValue(), RegexPattern.ALPHABET_VALIDATE_PATTERN);

                    if (DataValidation.isUserLengthFieldsValid(request)) {
                        User updateUser = new User.Builder()
                                .addId(id)
                                .addLogin(login)
                                .addFullName(fullName)
                                .addRole(role)
                                .build();
                        try {
                            userService.update(updateUser);
                            logger.log(Level.INFO, "Update user " + updatedUser.getLogin());
                            session.setAttribute(AttributeName.MESSAGE, "105 " + updatedUser.getLogin());
                            return ActionType.EDITUSERS;
                        } catch (ServiceException e) {
                            session.setAttribute(AttributeName.ERROR, e);
                            logger.log(Level.ERROR, e);
                            return ActionType.ERROR;
                        }
                    }
                }
            }

            ActionType returnActionType;
            try {
                User updatedUser = userService.findById(id);
                if (updatedUser != null) {
                    request.setAttribute(AttributeName.USER, updatedUser);
                    returnActionType = ActionType.UPDATEUSER;
                } else {
                    logger.log(Level.ERROR, "null user");
                    returnActionType = ActionType.ERROR;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                returnActionType = ActionType.ERROR;
            }
            return returnActionType;
        } else {
            return actionType;
        }
    }
}
