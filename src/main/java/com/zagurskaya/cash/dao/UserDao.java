package com.zagurskaya.cash.dao;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;

public interface UserDao extends Dao<User> {
    User getUserByLoginAndPassword(String login, String password) throws DAOException;
}
