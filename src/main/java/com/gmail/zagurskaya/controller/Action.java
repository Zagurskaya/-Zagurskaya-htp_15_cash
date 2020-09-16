package com.gmail.zagurskaya.controller;
import javax.servlet.http.HttpServletRequest;

public enum Action {

    INDEX(new CmdIndex()),
    ERROR(new CmdError()),
    LOGIN(new CmdLogin()),
    PROFILE(new CmdProfile()),

    ;

    Cmd command;
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

    Action(Cmd command) {
        this.command = command;
    }

    String getJsp() {
        return "/jsp/" + PATH + name().toLowerCase() + ".jsp";
    }

    static Action define(HttpServletRequest req) {
        try {
            String command = req.getParameter("command").toUpperCase();
            return Action.valueOf(command);
        } catch (Exception e) {
            return Action.INDEX;
        }
    }


}
