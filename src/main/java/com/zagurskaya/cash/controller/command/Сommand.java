package com.zagurskaya.cash.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Сommand {
    Action execute(HttpServletRequest request) throws Exception;
}
