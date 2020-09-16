package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public class CmdLogout implements Cmd {
    @Override
    public Action execute(HttpServletRequest req) {
        req.getSession().invalidate();
        return Action.LOGIN;
    }
}
