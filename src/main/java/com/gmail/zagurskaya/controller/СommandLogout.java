package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public class СommandLogout implements Сommand {
    @Override
    public Action execute(HttpServletRequest request) {
        request.getSession().invalidate();
//        request.getSession().removeAttribute("user");
        return Action.LOGIN;
    }
}
