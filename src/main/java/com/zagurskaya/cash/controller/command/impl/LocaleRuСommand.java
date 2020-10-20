package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.RequestDataUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Смена локали на RU".
 */
public class LocaleRuСommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public LocaleRuСommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        ActionType previousActionType = (ActionType) session.getAttribute(AttributeName.PREVIOUS_ACTION);
        ActionType actionType = previousActionType == null ? ActionType.INDEX : previousActionType;

        Cookie localEnCookie = new Cookie(AttributeName.LOCAL, "ru");
        RequestDataUtil.setCookie(request, localEnCookie);
        return actionType;
    }
}
