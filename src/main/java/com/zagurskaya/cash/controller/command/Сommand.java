package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    ActionType execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
