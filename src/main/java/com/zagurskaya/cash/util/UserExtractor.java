package com.zagurskaya.cash.util;

import com.zagurskaya.cash.entity.RoleEnum;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.SiteDataValidationException;

import javax.servlet.http.HttpServletRequest;

public class UserExtractor {

    public static void setUserNotCheckedFieldsToUser(HttpServletRequest request, User user) throws SiteDataValidationException {
        String login = DataUtil.getString(request, "login");
        String password = DataUtil.getString(request, "password");
        String role = DataUtil.getString(request, "role");

        user.setLogin(login);
        user.setPassword(password);
        user.setRole(RoleEnum.valueOf(role.toUpperCase()));
    }
    public static void setUpdateUserNotCheckedFieldsToUser(HttpServletRequest request, User user) throws SiteDataValidationException {
        String login = DataUtil.getString(request, "login");
        String role = DataUtil.getString(request, "role");

        user.setLogin(login);
        user.setRole(RoleEnum.valueOf(role.toUpperCase()));
    }
}
