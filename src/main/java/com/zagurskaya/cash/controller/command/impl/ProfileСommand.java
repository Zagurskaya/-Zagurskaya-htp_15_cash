package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileСommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(LoginСommand.class);
    private UserService userService = new UserServiceImpl();

    public ProfileСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        User user = DataUtil.findUser(request);
        try {
            if (user != null) {
                List<User> users = userService.findAll();
                request.setAttribute("users", users);

                return Action.PROFILE;
            }
        } catch (ServiceException e) {
            session.setAttribute("error", e);
            logger.log(Level.ERROR, e);
            return Action.ERROR;
        }
        return Action.LOGIN;
    }
}
