package com.gmail.zagurskaya.dao;

import com.gmail.zagurskaya.beans.User;
import com.gmail.zagurskaya.exception.DAOException;

public interface UserDao extends Dao<User> {
    User getUserByLoginAndPassword(String login, String password) throws DAOException;
}
