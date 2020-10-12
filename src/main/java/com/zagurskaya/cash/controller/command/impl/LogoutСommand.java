package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.constant.AttributeConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutСommand extends AbstractСommand {

    public LogoutСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        session.removeAttribute("message");
        session.removeAttribute("error");

        session.removeAttribute(AttributeConstant.USER);
        request.getSession().invalidate();
//        DataUtil.deleteCookie(request, AttributeConstant.LOGIN);
//        DataUtil.deleteCookie(request,AttributeConstant.ROLE);
//        request.getSession().removeAttribute(AttributeConstant.USER);
//        request.getSession().invalidate();
        return Action.LOGIN;
    }
}
