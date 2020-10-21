package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Команда
 */
public interface Сommand {
    /**
     * Путь к jsp странице
     *
     * @return путь
     */
    String getDirectoryPath();

    /**
     * Выполнение действия
     *
     * @param request - запрос
     * @return следующее действие
     */
    ActionType execute(HttpServletRequest request) throws CommandException;
}
