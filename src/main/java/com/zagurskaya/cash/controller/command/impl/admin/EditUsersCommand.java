package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditUsersCommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(EditUsersCommand.class);
    private UserService userService = new UserServiceImpl();

    public EditUsersCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) throws SiteDataValidationException, ServiceConstraintViolationException {
        final HttpSession session = request.getSession(false);

        if (DataUtil.isCreateUpdateDeleteOperation(request)) {
            Long id = DataUtil.getLong(request, AttributeConstant.ID);

            try {
                if (DataUtil.isUpdateOperation(request)) {
                    if (userService.findById(id) != null) {
                        final User updateUser = userService.findById(id);
                        session.setAttribute(AttributeConstant.USER, updateUser);
                        session.setAttribute(AttributeConstant.ID, id);
                        return Action.UPDATEUSER;
                    }
                } else if (DataUtil.isDeleteOperation(request)) {
                    User deleteUser = userService.findById(id);
                    if (deleteUser != null)
                        userService.delete(deleteUser);
                }
            } catch (ServiceException e) {
                session.setAttribute(AttributeConstant.ERROR, e);
                logger.log(Level.ERROR, e);
                return Action.ERROR;
            }
        }
        try {

            int page = 1;
            if (request.getParameter(AttributeConstant.PAGE) != null)
                page = Integer.parseInt(request.getParameter(AttributeConstant.PAGE));

            int numberOfPages = (int) Math.ceil(userService.countRows() * 1.0 / AttributeConstant.RECORDS_PER_PAGE);
            List<User> users = userService.onePartOfUsersListOnPage(page);

            request.setAttribute(AttributeConstant.NUMBER_OF_PAGE, numberOfPages);
            request.setAttribute(AttributeConstant.CURRENT_PAGE, page);
            request.setAttribute(AttributeConstant.USERS, users);
            return Action.EDITUSERS;

        } catch (ServiceException e) {
            session.setAttribute(AttributeConstant.ERROR, e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
