package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;

public interface UserDao extends Dao<User> {
    /**
     * Поиск пользователя по логину
     *
     * @param login - логин
     * @return пользователь
     * @throws DAOException ошибке доступа к базе данных или других ошибках.
     */
    User findByLogin(String login) throws DAOException;
}
