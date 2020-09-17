package com.zagurskaya.cash.controller;

import javax.servlet.http.HttpServletRequest;

public interface Сommand {
    Action execute(HttpServletRequest request) throws Exception;
}
