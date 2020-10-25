package com.zagurskaya.cash.controller.util;

import com.zagurskaya.cash.controller.command.AttributeName;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * User data extractor
 */
public class UserExtractor {
    /**
     * Getting initial data from a request and setting it in the appropriate fields of the created user
     *
     * @param request - request
     * @return user
     * @throws CommandException user data validation error.
     */
    public User userNotCheckedFieldsToUser(HttpServletRequest request) throws CommandException {
        String login = ControllerDataUtil.getString(request, AttributeName.LOGIN);
        String fullName = ControllerDataUtil.getString(request, AttributeName.FULL_MANE);
        String role = ControllerDataUtil.getString(request, AttributeName.ROLE);
        return new User.Builder()
                .addLogin(login)
                .addFullName(fullName)
                .addRole(role)
                .build();
    }
}
