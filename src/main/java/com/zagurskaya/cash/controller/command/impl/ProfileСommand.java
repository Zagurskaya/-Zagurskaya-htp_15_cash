package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileСommand extends AbstractСommand {
    private static final Logger logger = LogManager.getLogger(LoginСommand.class);
    private UserService userService = new UserServiceImpl();


    public ProfileСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (DataUtil.isCreateUpdateDeleteOperation(request)) {
//            session.removeAttribute(AttributeConstant.USER);
//            request.getSession().invalidate();
            DataUtil.deleteCookie(request, AttributeConstant.LOGIN);
            DataUtil.deleteCookie(request, AttributeConstant.ROLE);
            request.getSession().removeAttribute(AttributeConstant.USER);
            request.getSession().invalidate();
            return Action.INDEX;
        }
        User user = DataUtil.findUser(request);
        if (user != null) {
            return Action.PROFILE;
        } else {
            return Action.LOGIN;
        }
    }
}
