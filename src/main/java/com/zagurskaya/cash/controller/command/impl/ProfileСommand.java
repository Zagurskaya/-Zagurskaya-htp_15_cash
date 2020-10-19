package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.util.RequestDataUtil;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.DataValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Профайл".
 */
public class ProfileСommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param path - путь
     */
    public ProfileСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("message");
        session.removeAttribute("error");
        User user = RequestDataUtil.findUser(request);

        if (user != null) {
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {

                RequestDataUtil.deleteCookie(request, AttributeName.LOGIN);
                RequestDataUtil.deleteCookie(request, AttributeName.ROLE);
                request.getSession().removeAttribute(AttributeName.USER);
                request.getSession().invalidate();
                return Action.INDEX;
            }
            return Action.PROFILE;
        } else {
            return Action.LOGIN;
        }
    }
}
