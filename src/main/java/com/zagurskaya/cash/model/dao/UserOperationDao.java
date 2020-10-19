package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.DAOException;

import java.util.List;

public interface UserOperationDao extends Dao<UserOperation> {
    /**
     * Получение списка проведенных операций
     *
     * @return список проведенных операций
     */
    List<UserOperation> findAll() throws DAOException;

    List<UserOperation> findAllByUserIdAndDutiesId(Long userId, Long dutiesId,int limit, int startPosition) throws DAOException;
}
