package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractCommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.DataValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The action is "Profile".
 */
public class ProfileCommand extends AbstractCommand {

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public ProfileCommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute(AttributeName.MESSAGE);
        session.removeAttribute(AttributeName.ERROR);
        User user = RequestDataUtil.findUser(request);

        if (user != null) {
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {

                request.getSession().removeAttribute(AttributeName.USER);
                return ActionType.INDEX;
            }
            return ActionType.PROFILE;
        } else {
            return ActionType.LOGIN;
        }
    }
}
