package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class MainCommand extends AbstractСommand {

    public MainCommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {

        Action action = actionAfterValidationUserAndPermission(request, Action.MAIN);
        return action;
    }
}
