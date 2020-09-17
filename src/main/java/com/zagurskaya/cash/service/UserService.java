package com.zagurskaya.cash.service;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;

public interface UserService extends Service<User> {

    User getUserByLoginAndPassword(String login, String password) throws ServiceException;
}
