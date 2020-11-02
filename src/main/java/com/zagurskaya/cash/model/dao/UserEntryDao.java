package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.UserEntry;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface UserEntryDao extends Dao<UserEntry> {
    /**
     * Find a list of executed entry
     *
     * @return list of executed entry
     * @throws DaoException database access error or other errors
     */
    List<UserEntry> findAll() throws DaoException;

    /**
     * Get success list entries by operation Id
     *
     * @param id - operation id
     * @return list user operation
     * @throws DaoException database access error or other errors
     */
    List<UserEntry> findUserEntriesByOperationId(Long id) throws DaoException;
}
