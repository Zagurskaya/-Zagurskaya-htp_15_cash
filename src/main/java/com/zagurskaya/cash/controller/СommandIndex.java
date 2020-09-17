package com.zagurskaya.cash.controller;

import javax.servlet.http.HttpServletRequest;

public class СommandIndex implements Сommand {
    @Override
    public Action execute(HttpServletRequest request) {
        Action.INDEX.setPATH("/");
        return Action.INDEX;
    }
}
