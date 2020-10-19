package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
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
    public ActionType execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        ActionType previousActionType = (ActionType) session.getAttribute(AttributeName.PREVIOUS_ACTION);
        ActionType actionType = previousActionType == null ? ActionType.INDEX : previousActionType;

        Cookie localCookie = new Cookie(AttributeName.LOCAL, "en");
        RequestDataUtil.setCookie(request, localCookie);
        return actionType;
    }
}
