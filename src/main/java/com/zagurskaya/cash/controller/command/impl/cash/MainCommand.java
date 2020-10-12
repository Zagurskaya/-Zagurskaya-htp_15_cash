package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainCommand extends AbstractСommand {

    public MainCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("message");
        session.removeAttribute("error");

        Action action = actionAfterValidationUserAndPermission(request, Action.MAIN);
        return action;
    }
}
