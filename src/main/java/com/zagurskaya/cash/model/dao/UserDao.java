package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    /**
     * Поиск пользователя по логину
     *
     * @param login - логин
     * @return пользователь
     * @throws DaoException ошибке доступа к базе данных или других ошибках.
     */
    User findByLogin(String login) throws DaoException;

    /**
     * Получение списка пользователей
     *
     * @return список пользователей
     */
    List<User> findAll() throws DaoException;
}
