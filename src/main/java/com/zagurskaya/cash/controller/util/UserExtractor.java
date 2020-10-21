package com.zagurskaya.cash.controller.util;

import com.zagurskaya.cash.controller.command.AttributeName;
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
        String login = RequestDataUtil.getString(request, AttributeName.LOGIN);
        String password = RequestDataUtil.getString(request, AttributeName.PASSWORD);
        String fullName = RequestDataUtil.getString(request, AttributeName.FULL_MANE);
        String role = RequestDataUtil.getString(request, AttributeName.ROLE);
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
        String login = RequestDataUtil.getString(request, AttributeName.LOGIN);
        String fullName = RequestDataUtil.getString(request, AttributeName.FULL_MANE);
        String role = RequestDataUtil.getString(request, AttributeName.ROLE);

        return new User.Builder()
                .addLogin(login)
                .addFullName(fullName)
                .addRole(role)
                .build();
    }
}
