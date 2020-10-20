package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;
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
     * @param directoryPath - путь
     */
    public LogoutСommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("message");
        session.removeAttribute("error");

        session.removeAttribute(AttributeName.USER);
        return ActionType.LOGIN;
    }
}
