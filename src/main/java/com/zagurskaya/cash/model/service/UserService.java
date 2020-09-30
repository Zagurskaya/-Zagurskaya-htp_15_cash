package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface UserService extends Service<User> {

    User getUserByLoginAndValidPassword(String login, String password) throws ServiceException;

    Long countRows() throws ServiceException;

    List<User> onePartOfUsersListOnPage(int page) throws ServiceException;

}
