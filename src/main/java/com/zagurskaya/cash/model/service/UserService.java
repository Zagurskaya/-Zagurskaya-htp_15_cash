package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface UserService extends Service<User> {
    /**
     * Search for a user by login and valid password
     *
     * @param login    - login
     * @param password - password
     * @return user
     * @throws ServiceException error during execution of logical blocks and actions
     */
    User findUserByLoginAndValidPassword(String login, String password) throws ServiceException;

    /**
     * Get user List
     *
     * @return user List
     */
    List<User> findAll() throws ServiceException;

    /**
     * Create user with password
     *
     * @param user - user
     * @return true on successful createCheckEn
     */
    boolean create(User user, String password) throws ServiceException, CommandException;
}
