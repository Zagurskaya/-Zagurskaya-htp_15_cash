package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;

public interface UserService extends Service<User> {

    User getUserByLoginAndPassword(String login, String password) throws ServiceException;
}
