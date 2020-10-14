package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;

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
     * @param path - путь
     */
    public LocaleRuСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        Action previousAction = (Action) session.getAttribute(AttributeConstant.PREVIOUS_ACTION);
        Action action = previousAction == null ? Action.INDEX : previousAction;

        Cookie localEnCookie = new Cookie(AttributeConstant.LOCAL, "ru");
        DataUtil.setCookie(request, localEnCookie);
        return action;
    }
}
