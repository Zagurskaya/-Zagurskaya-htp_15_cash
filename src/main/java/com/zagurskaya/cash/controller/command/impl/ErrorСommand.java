package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.constant.AttributeConstant;

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

        request.setAttribute(AttributeConstant.ERROR, session.getAttribute(AttributeConstant.ERROR));
        session.removeAttribute(AttributeConstant.ERROR);
        return Action.ERROR;
    }
}
