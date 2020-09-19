package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.Сommand;

import javax.servlet.http.HttpServletRequest;

public class СommandError implements Сommand {

    @Override
    public Action execute(HttpServletRequest request) {
        Action.ERROR.setPATH("/");
        return Action.ERROR;
    }
}
