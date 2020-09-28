package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalEnСommand extends AbstractСommand {

    public LocalEnСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        Action previousAction = (Action) session.getAttribute("previousAction");

        session.getAttribute("previousAction");
        session.setAttribute("local", "en");
        return previousAction;
    }
}
