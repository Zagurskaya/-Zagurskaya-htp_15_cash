package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.Сommand;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.model.service.UserService;
import com.zagurskaya.cash.model.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.Form;
import com.zagurskaya.cash.util.PatternConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginСommand implements Сommand {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private UserService userService = new UserServiceImpl();

    @Override
    public Action execute(HttpServletRequest request) throws Exception {
        if (Form.isPost(request)) {
            String login = Form.getString(request, LOGIN, PatternConstant.LOGIN_VALIDATE_PATTERN);
            String password = Form.getString(request, PASSWORD, PatternConstant.PASSWORD_VALIDATE_PATTERN);
            User user = userService.getUserByLoginAndPassword(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return Action.PROFILE;
            }
        }
        return Action.LOGIN;
    }
}
