package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public class CmdError implements Cmd {

    @Override
    public Action execute(HttpServletRequest req) {
        Action.ERROR.setPATH("/");
        return Action.ERROR;
    }
}
