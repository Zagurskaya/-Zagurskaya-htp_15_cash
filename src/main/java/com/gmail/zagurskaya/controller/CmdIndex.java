package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public class CmdIndex implements Cmd {
    @Override
    public Action execute(HttpServletRequest req) {
        Action.INDEX.setPATH("/");
        return Action.INDEX;
    }
}
