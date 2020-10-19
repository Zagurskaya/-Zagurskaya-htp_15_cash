package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface UserOperationDao extends Dao<UserOperation> {
    /**
     * Получение списка проведенных операций
     *
     * @return список проведенных операций
     */
    List<UserOperation> findAll() throws DaoException;

    List<UserOperation> findAllByUserIdAndDutiesId(Long userId, Long dutiesId,int limit, int startPosition) throws DaoException;
}
