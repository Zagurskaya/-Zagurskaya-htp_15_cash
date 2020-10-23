package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractCommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.controller.util.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The action is "Users".
 */
public class EditUsersCommand extends AbstractCommand {
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);
    private UserService userService = new UserServiceImpl();

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public EditUsersCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute(AttributeName.ERROR);

        try {
            ActionType actionType = actionAfterValidationUserAndPermission(request, ActionType.EDITUSERS);
            if (actionType == ActionType.EDITUSERS) {
                if (DataValidation.isCreateUpdateDeleteOperation(request)) {
                    Long id = DataUtil.getLong(request, AttributeName.ID);

                    try {
                        if (DataValidation.isUpdateOperation(request)) {
                            if (userService.findById(id) != null) {
                                session.setAttribute(AttributeName.ID, id);
                                return ActionType.UPDATEUSER;
                            }
                        } else if (DataValidation.isDeleteOperation(request)) {
                            User deleteUser = userService.findById(id);
                            if (deleteUser != null) {
                                userService.delete(deleteUser);
                                logger.log(Level.INFO, "Delete user " + deleteUser.getLogin());
                                session.setAttribute(AttributeName.MESSAGE, "106 " + deleteUser.getLogin());
                            }
                        }
                    } catch (ServiceException e) {
                        session.setAttribute(AttributeName.ERROR, e);
                        logger.log(Level.ERROR, e);
                        return ActionType.ERROR;
                    }
                }

                int page = 1;
                if (request.getParameter(AttributeName.PAGE) != null)
                    page = Integer.parseInt(request.getParameter(AttributeName.PAGE));

                int numberOfPages = (int) Math.ceil(userService.countRows() * 1.0 / AttributeName.RECORDS_PER_PAGE);
                List<User> users = userService.onePartOfListOnPage(page);

                request.setAttribute(AttributeName.NUMBER_OF_PAGE, numberOfPages);
                request.setAttribute(AttributeName.CURRENT_PAGE, page);
                request.setAttribute(AttributeName.USERS, users);
                return ActionType.EDITUSERS;
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
