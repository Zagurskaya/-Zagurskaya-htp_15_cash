package com.zagurskaya.cash.controller.command.impl.admin;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand extends AbstractСommand {

    public AdminCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {

        Action action = actionAfterValidationUserAndPermission(request, Action.ADMIN);
        return action;
    }
}
