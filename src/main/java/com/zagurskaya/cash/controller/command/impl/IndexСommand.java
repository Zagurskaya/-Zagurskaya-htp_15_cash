package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;

import javax.servlet.http.HttpServletRequest;

public class IndexСommand extends AbstractСommand {
    public IndexСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {

        return Action.INDEX;
    }
}
