package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The action is "Error page".
 */
public class ErrorCommand implements Command {
    private String directoryPath;

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public ErrorCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {

        return ActionType.ERROR;
    }
}
