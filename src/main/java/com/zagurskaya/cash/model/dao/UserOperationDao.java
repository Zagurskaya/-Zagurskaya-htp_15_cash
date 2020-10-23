package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface UserOperationDao extends Dao<UserOperation> {
    /**
     * Find a list of executed operations
     *
     * @return list of executed operations
     * @throws DaoException database access error or other errors
     */
    List<UserOperation> findAll() throws DaoException;

    /**
     * Find a list of executed operations by user and duties
     *
     * @return list of executed operations
     * @throws DaoException database access error or other errors
     */
    List<UserOperation> findAllByUserIdAndDutiesId(Long userId, Long dutiesId, int limit, int startPosition) throws DaoException;
}
