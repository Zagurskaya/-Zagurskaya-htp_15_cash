package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;

public interface UserDao extends Dao<User> {

    User findByLogin(String login) throws DAOException;

    Long countRows() throws DAOException;

}
