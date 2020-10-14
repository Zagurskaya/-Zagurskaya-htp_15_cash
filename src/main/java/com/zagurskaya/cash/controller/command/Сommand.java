package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;

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
    String getPath();

    /**
     * Выполнение действия
     *
     * @param request - запрос
     * @return следующее действие
     */
    Action execute(HttpServletRequest request) throws SiteDataValidationException, ServiceException, ServiceConstraintViolationException;
}
