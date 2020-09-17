package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.entity.User;
import com.gmail.zagurskaya.exception.ServiceException;

public interface UserService extends Service<User> {

    User getUserByLoginAndPassword(String login, String password) throws ServiceException;
}
