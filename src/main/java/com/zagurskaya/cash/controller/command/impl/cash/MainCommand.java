package com.zagurskaya.cash.controller.command.impl.cash;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The action is "Cashier Home".
 */
public class MainCommand implements Command {
    private String directoryPath;

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public MainCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        ControllerDataUtil.removeAttributeMessage(request);
        ControllerDataUtil.removeAttributeError(request);
        return ActionType.MAIN;
    }
}
