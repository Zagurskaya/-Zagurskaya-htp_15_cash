package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface SprOperationDao extends Dao<SprOperation> {
    /**
     * Получение списка операций
     *
     * @return список операций
     */
    List<SprOperation> findAll() throws DaoException;

}
