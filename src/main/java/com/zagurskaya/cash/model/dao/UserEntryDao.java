package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface UserEntryDao extends Dao<UserEntry> {
    /**
     * Получение списка проведенных проводок
     *
     * @return список проведенных проводок
     */
    List<UserEntry> findAll() throws DaoException;
}
