package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.AttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "Конец сеанса пользователя".
 */
public class LogoutСommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param path - путь
     */
    public LogoutСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("message");
        session.removeAttribute("error");

        session.removeAttribute(AttributeName.USER);
        return Action.LOGIN;
    }
}
