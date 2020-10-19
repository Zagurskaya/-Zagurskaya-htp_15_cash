package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.controller.command.AttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Действие "ERROR".
 */
public class ErrorСommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param path - путь
     */
    public ErrorСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        request.setAttribute(AttributeName.ERROR, session.getAttribute(AttributeName.ERROR));
        session.removeAttribute(AttributeName.ERROR);
        return Action.ERROR;
    }
}
