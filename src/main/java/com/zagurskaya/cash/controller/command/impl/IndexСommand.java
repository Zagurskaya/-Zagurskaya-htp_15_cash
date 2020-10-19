package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.ActionType;

import javax.servlet.http.HttpServletRequest;

/**
 * Стартовая страница
 */
public class IndexСommand extends AbstractСommand {
    /**
     * Конструктор
     *
     * @param path - путь
     */
    public IndexСommand(String path) {
        super(path);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {

        return ActionType.INDEX;
    }
}
