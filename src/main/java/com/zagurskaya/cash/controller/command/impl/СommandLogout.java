package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.Сommand;

import javax.servlet.http.HttpServletRequest;

public class СommandLogout implements Сommand {
    @Override
    public Action execute(HttpServletRequest request) {
        request.getSession().invalidate();
//        request.getSession().removeAttribute("user");
        return Action.LOGIN;
    }
}
