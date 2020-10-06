package com.zagurskaya.cash.util;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.SiteDataValidationException;

import javax.servlet.http.HttpServletRequest;

public class UserExtractor {

    public static User userNotCheckedFieldsToUser(HttpServletRequest request) throws SiteDataValidationException {
        String login = DataUtil.getString(request, "login");
        String password = DataUtil.getString(request, "password");
        String fullName = DataUtil.getString(request, "fullname");
        String role = DataUtil.getString(request, "role");
        User user = new User
                .Builder()
                .addLogin(login)
                .addPassword(password)
                .addFullName(fullName)
                .addRole(role)
                .build();
        return user;
    }

    public static User updateUserNotCheckedFieldsToUser(HttpServletRequest request) throws SiteDataValidationException {
        String login = DataUtil.getString(request, "login");
        String fullName = DataUtil.getString(request, "fullname");
        String role = DataUtil.getString(request, "role");

        User user = new User
                .Builder()
                .addLogin(login)
                .addFullName(fullName)
                .addRole(role)
                .build();
        return user;
    }
}
