package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public interface Сommand {
    Action execute(HttpServletRequest request) throws Exception;
}
