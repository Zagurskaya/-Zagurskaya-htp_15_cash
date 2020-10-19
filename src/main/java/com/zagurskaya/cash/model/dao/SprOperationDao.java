package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.DAOException;

import java.util.List;

public interface SprOperationDao extends Dao<SprOperation> {
    /**
     * Получение списка операций
     *
     * @return список операций
     */
    List<SprOperation> findAll() throws DAOException;

}
