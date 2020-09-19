package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.Сommand;

import javax.servlet.http.HttpServletRequest;

public class СommandIndex implements Сommand {
    @Override
    public Action execute(HttpServletRequest request) {
        Action.INDEX.setPATH("/");
        return Action.INDEX;
    }
}
