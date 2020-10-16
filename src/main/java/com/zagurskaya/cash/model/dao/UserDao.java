package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;

import java.util.List;

public interface UserDao extends Dao<User> {
    /**
     * Поиск пользователя по логину
     *
     * @param login - логин
     * @return пользователь
     * @throws DAOException ошибке доступа к базе данных или других ошибках.
     */
    User findByLogin(String login) throws DAOException;

    /**
     * Получение списка пользователей
     *
     * @return список пользователей
     */
    List<User> findAll() throws DAOException;
}
