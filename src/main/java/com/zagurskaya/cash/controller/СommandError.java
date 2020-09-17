package com.zagurskaya.cash.controller;

import javax.servlet.http.HttpServletRequest;

public class СommandError implements Сommand {

    @Override
    public Action execute(HttpServletRequest request) {
        Action.ERROR.setPATH("/");
        return Action.ERROR;
    }
}
