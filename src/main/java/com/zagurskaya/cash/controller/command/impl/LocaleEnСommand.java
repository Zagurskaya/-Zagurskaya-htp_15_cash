package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.RequestDataUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Смена локали на EN".
 */
public class LocaleEnСommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param path - путь
     */
    public LocaleEnСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        Action previousAction = (Action) session.getAttribute(AttributeName.PREVIOUS_ACTION);
        Action action = previousAction == null ? Action.INDEX : previousAction;

        Cookie localCookie = new Cookie(AttributeName.LOCAL, "en");
        RequestDataUtil.setCookie(request, localCookie);
        return action;
    }
}
