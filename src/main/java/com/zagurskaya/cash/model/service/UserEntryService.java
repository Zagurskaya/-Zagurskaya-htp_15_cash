package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface UserEntryService {

    /**
     * Get entry List by today
     *
     * @return user List
     */
    List<UserEntry> findAllToday() throws ServiceException;
}
