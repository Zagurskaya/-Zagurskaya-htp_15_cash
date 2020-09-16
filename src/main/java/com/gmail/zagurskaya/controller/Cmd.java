package com.gmail.zagurskaya.controller;

import javax.servlet.http.HttpServletRequest;

public interface Cmd {
    Action execute(HttpServletRequest req) throws Exception;
}
