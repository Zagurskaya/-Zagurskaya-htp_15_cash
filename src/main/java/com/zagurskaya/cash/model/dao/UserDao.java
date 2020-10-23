package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    /**
     * Find a list of user by login
     *
     * @param login - login
     * @return user
     * @throws DaoException database access error or other errors
     */
    User findByLogin(String login) throws DaoException;

    /**
     * Find a list of users
     *
     * @return list of users
     * @throws DaoException database access error or other errors
     */
    List<User> findAll() throws DaoException;

    /**
     * Create user with password
     *
     * @param user - user
     * @return true on successful delete
     * @throws DaoException                    database access error or other errors
     * @throws DaoConstraintViolationException duplication data
     */
    Long create(User user, String password) throws DaoConstraintViolationException, DaoException;

    /**
     * Find a passwor by login
     *
     * @param login - user login
     * @return true on successful delete
     * @throws DaoException database access error or other errors
     */
    String findPasswordByLogin(String login) throws DaoException;
}
