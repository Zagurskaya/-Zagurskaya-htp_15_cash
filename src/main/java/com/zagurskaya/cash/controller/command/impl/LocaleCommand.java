package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.ActionType;
import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.controller.command.Command;
import com.zagurskaya.cash.controller.util.ControllerDataUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The action is "Change locale".
 */
public class LocaleCommand implements Command {
    private String directoryPath;
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_EN = "en";

    /**
     * Constructor
     *
     * @param directoryPath - path
     */
    public LocaleCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public String getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public ActionType execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession(false);
        ActionType previousActionType = (ActionType) session.getAttribute(AttributeName.CURRENT_ACTION_TYPE);
        ActionType actionType = previousActionType == null ? ActionType.INDEX : previousActionType;
        Cookie localeCookie = ControllerDataUtil.getCookie(request, AttributeName.LOCAL);
        String locale = localeCookie != null
                ? (localeCookie.getValue().equals(LOCALE_RU) ? LOCALE_EN : LOCALE_RU) : LOCALE_RU;

        Cookie localEnCookie = new Cookie(AttributeName.LOCAL, locale);
        response.addCookie(localEnCookie);
        return actionType;
    }
}
