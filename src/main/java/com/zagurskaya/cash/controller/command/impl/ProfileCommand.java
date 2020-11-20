package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.util.ControllerDataUtil;
import com.zagurskaya.cash.util.DataValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The action is "Profile".
 */
public class ProfileCommand implements Command {
    private String directoryPath;

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public ProfileCommand(String directoryPath) {
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

        if (DataValidation.isCreateUpdateDeleteOperation(request)) {
            ControllerDataUtil.removeAttributeUser(request);
            return ActionType.INDEX;
        }
        return ActionType.PROFILE;
    }
}
