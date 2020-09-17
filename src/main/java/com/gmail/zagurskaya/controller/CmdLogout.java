package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public class CmdLogout implements Cmd {
    @Override
    public Action execute(HttpServletRequest request) {
        request.getSession().invalidate();
//        request.getSession().removeAttribute("user");
        return Action.LOGIN;
    }
}
