package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;
import com.zagurskaya.cash.exception.SiteDataValidationException;

import javax.servlet.http.HttpServletRequest;

public interface Сommand {
    String getPath();

    Action execute(HttpServletRequest request) throws SiteDataValidationException, ServiceException, ServiceConstraintViolationException;
}
