package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class ErrorСommand extends AbstractСommand {

    public ErrorСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        return Action.ERROR;
    }
}
