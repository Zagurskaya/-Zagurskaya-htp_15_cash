package com.zagurskaya.cash.controller.util;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Экстрактор данных пользователя
 */
public class UserExtractor {
    /**
     * Получение исходных данных из запроса и установка их в соответствующие поля создаваемого пользователя
     *
     * @param request - запрос
     * @return пользователь
     * @throws CommandException ошибке валидации данных пользователя.
     */
    public User userNotCheckedFieldsToUser(HttpServletRequest request) throws CommandException {
        String login = RequestDataUtil.getString(request, "login");
        String password = RequestDataUtil.getString(request, "password");
        String fullName = RequestDataUtil.getString(request, "fullname");
        String role = RequestDataUtil.getString(request, "role");
        return new User.Builder()
                .addLogin(login)
                .addPassword(password)
                .addFullName(fullName)
                .addRole(role)
                .build();
    }

    /**
     * Получение исходных данных из запроса и установка их в соответствующие поля обновляемого пользователя
     *
     * @param request - запрос
     * @return пользователь
     * @throws CommandException ошибке валидации данных пользователя.
     */
    public User updateUserNotCheckedFieldsToUser(HttpServletRequest request) throws CommandException {
        String login = RequestDataUtil.getString(request, "login");
        String fullName = RequestDataUtil.getString(request, "fullname");
        String role = RequestDataUtil.getString(request, "role");

        return new User.Builder()
                .addLogin(login)
                .addFullName(fullName)
                .addRole(role)
                .build();
    }
}
