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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
            Long id = DataUtil.getLong(request, "id");

            try {
                if (DataUtil.isUpdateOperation(request)) {
                    if (userService.findById(id) != null) {
                        final User updateUser = userService.findById(id);
                        session.setAttribute("user", updateUser);
                        session.setAttribute("id", id);
                        return Action.UPDATEUSER;
                    }
                } else if (DataUtil.isDeleteOperation(request)) {
                    User deleteUser = userService.findById(id);
                    if (deleteUser != null)
                        userService.delete(deleteUser);
                }
            } catch (ServiceException e) {
                session.setAttribute("error", e);
                logger.log(Level.ERROR, e);
                return Action.ERROR;
            }
        }
        try {
            List<User> users = new ArrayList<>();
            users.addAll(userService.findAll());
            request.setAttribute("users", users);
            return Action.EDITUSERS;

        } catch (ServiceException e) {
            session.setAttribute("error", e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
    }
}
