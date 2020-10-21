package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.util.RequestDataUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Действие "Смена локали на RU".
 */
public class LocaleСommand extends AbstractСommand {
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_EN = "en";

    /**
     * Конструктор
     *
     * @param directoryPath - путь
     */
    public LocaleСommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession(false);
        ActionType previousActionType = (ActionType) session.getAttribute(AttributeName.CURRENT_ACTION_TYPE);
        ActionType actionType = previousActionType == null ? ActionType.INDEX : previousActionType;
        Cookie localeCookie = RequestDataUtil.getCookie(request, AttributeName.LOCAL);
        String locale = localeCookie != null
                ? (localeCookie.getValue().equals(LOCALE_RU) ? LOCALE_EN : LOCALE_RU) : LOCALE_RU;

        Cookie localEnCookie = new Cookie(AttributeName.LOCAL, locale);
        response.addCookie(localEnCookie);
        return actionType;
    }
}
