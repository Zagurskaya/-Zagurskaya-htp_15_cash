package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface SprOperationDao extends Dao<SprOperation> {
    /**
     * Find a list of operations
     *
     * @return list of operations
     * @throws DaoException database access error or other errors
     */
    List<SprOperation> findAll() throws DaoException;
}
