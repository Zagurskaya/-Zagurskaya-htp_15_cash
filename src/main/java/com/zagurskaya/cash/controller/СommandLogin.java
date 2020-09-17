package com.zagurskaya.cash.controller;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.service.UserService;
import com.zagurskaya.cash.service.impl.UserServiceImpl;
import com.zagurskaya.cash.util.Form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class СommandLogin implements Сommand {
    private UserService userService = new UserServiceImpl();

    @Override
    public Action execute(HttpServletRequest request) throws Exception {
        if (Form.isPost(request)) {
            String login = Form.getString(request, "login", "[a-zA-Z0-9_-]{5,}");
            String password = Form.getString(request, "password", "[a-zA-Z0-9_-]{5,}");
            User user = userService.getUserByLoginAndPassword(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                Action.PROFILE.setPATH("/");
                return Action.PROFILE;
            }
        }
        Action.LOGIN.setPATH("/");
        return Action.LOGIN;
    }
}
