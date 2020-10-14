package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface UserService extends Service<User> {
    /**
     * Поиск пользователя по логину  и валидному паролю
     *
     * @param login    - логин
     * @param password - пароль
     * @return пользователь
     * @throws ServiceException ошибке во время выполнения логическтх блоков и действий.
     */
    User getUserByLoginAndValidPassword(String login, String password) throws ServiceException;

    /**
     * Количество строк в таблите пользователей
     *
     * @return количество строк
     * @throws ServiceException ошибке во время выполнения логическтх блоков и действий.
     */
    Long countRows() throws ServiceException;

    /**
     * Получение списка пользователь на определенной странице
     *
     * @param page - номер страницы
     * @return список пользователей
     */
    List<User> onePartOfUsersListOnPage(int page) throws ServiceException;

}
