package com.zagurskaya.cash.controller.command.impl;

import com.zagurskaya.cash.controller.command.AbstractСommand;
import com.zagurskaya.cash.controller.command.Action;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.constant.AttributeConstant;
import com.zagurskaya.cash.util.DataUtil;
import com.zagurskaya.cash.validation.DataValidation;

import javax.servlet.http.HttpServletRequest;

public class ProfileСommand extends AbstractСommand {

    public ProfileСommand(String path) {
        super(path);
    }

    @Override
    public Action execute(HttpServletRequest request) {
        User user = DataUtil.findUser(request);

        if (user != null) {
            if (DataValidation.isCreateUpdateDeleteOperation(request)) {

                DataUtil.deleteCookie(request, AttributeConstant.LOGIN);
                DataUtil.deleteCookie(request, AttributeConstant.ROLE);
                request.getSession().removeAttribute(AttributeConstant.USER);
                request.getSession().invalidate();
                return Action.INDEX;
            }
            return Action.PROFILE;
        } else {
            return Action.LOGIN;
        }
    }
}
