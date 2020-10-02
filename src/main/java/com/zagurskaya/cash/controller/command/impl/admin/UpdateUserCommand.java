package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.RoleEnum;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.util.PatternConstant;
import com.zagurskaya.cash.util.UserExtractor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateUserCommand extends AbstractСommand {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);

    public UpdateUserCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) throws SiteDataValidationException, ServiceConstraintViolationException {
        final HttpSession session = request.getSession(false);
        final Long id = (Long) session.getAttribute(AttributeConstant.ID);
        if (id == null) return Action.INDEX;

        Action action = actionAfterValidationUserAndPermission(request, Action.EDITUSERS);
        if (action == Action.EDITUSERS) {


            if (DataUtil.isCreateUpdateDeleteOperation(request)) {

                if (DataUtil.isCancelOperation(request)) {
                    return Action.EDITUSERS;

                } else if (DataUtil.isSaveOperation(request)) {
                    User updatedUser = new User();
                    UserExtractor.setUpdateUserNotCheckedFieldsToUser(request, updatedUser);
                    request.setAttribute(AttributeConstant.USER, updatedUser);

                    String login = DataUtil.getString(updatedUser.getLogin(), PatternConstant.LOGIN_VALIDATE_PATTERN);
                    String role = DataUtil.getString(updatedUser.getRole().getValue(), PatternConstant.ROLE_VALIDATE_PATTERN);

                    User updateUser = new User();
                    updateUser.setId(id);
                    updateUser.setLogin(login);
                    updateUser.setRole(RoleEnum.valueOf(role.toUpperCase()));
                    try {
                        userService.update(updateUser);
                        return Action.EDITUSERS;
                    } catch (ServiceException e) {
                        session.setAttribute(AttributeConstant.ERROR, e);
                        logger.log(Level.ERROR, e);
                        return Action.ERROR;
                    }
                }
            }

            Action returnAction ;
            try {
                User updatedUser = userService.findById(id);
                if (updatedUser != null) {
                    request.setAttribute(AttributeConstant.USER, updatedUser);
                    returnAction = Action.UPDATEUSER;
                } else {
                    logger.log(Level.ERROR, "null user");
                    returnAction = Action.ERROR;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                returnAction = Action.ERROR;
            }
            return returnAction;
        } else {
            return action;
        }
    }
}
