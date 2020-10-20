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
     * @param directoryPath - путь
     */
    public IndexСommand(String directoryPath) {
        super(directoryPath);
    }

    @Override
    public ActionType execute(HttpServletRequest request) {

        return ActionType.INDEX;
    }
}
