package com.zagurskaya.cash.util;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.SiteDataValidationException;

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
     * @throws SiteDataValidationException ошибке валидации данных пользователя.
     */
    public User userNotCheckedFieldsToUser(HttpServletRequest request) throws SiteDataValidationException {
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

    /**
     * Получение исходных данных из запроса и установка их в соответствующие поля обновляемого пользователя
     *
     * @param request - запрос
     * @return пользователь
     * @throws SiteDataValidationException ошибке валидации данных пользователя.
     */
    public User updateUserNotCheckedFieldsToUser(HttpServletRequest request) throws SiteDataValidationException {
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
