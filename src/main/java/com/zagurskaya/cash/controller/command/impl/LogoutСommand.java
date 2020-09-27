package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class LogoutСommand extends AbstractСommand {

    public LogoutСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return Action.LOGIN;
    }
}
