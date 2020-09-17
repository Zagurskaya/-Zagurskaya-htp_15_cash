package com.zagurskaya.cash.controller;
import javax.servlet.http.HttpServletRequest;

public enum Action {

    INDEX(new СommandIndex()),
    ERROR(new СommandError()),
    LOGIN(new СommandLogin()),
    PROFILE(new СommandProfile()),

    ;

    Сommand command;
    String PATH = "";

    Action(String PATH) {
        this.PATH = PATH;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    Action(Сommand command) {
        this.command = command;
    }

    String getJsp() {
        return "/jsp/" + PATH + name().toLowerCase() + ".jsp";
    }

    static Action define(HttpServletRequest request) {
        try {
            String command = request.getParameter("command").toUpperCase();
            return Action.valueOf(command);
        } catch (Exception e) {
            return Action.INDEX;
        }
    }


}
