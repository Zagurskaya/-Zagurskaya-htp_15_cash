package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.SprEntry;
import com.zagurskaya.cash.exception.DAOException;

import java.util.List;

public interface SprEntryDao extends Dao<SprEntry> {
    /**
     * Получение списка проводок
     *
     * @return список проводок
     */
    List<SprEntry> findAll() throws DAOException;

    /**
     * Получение списка проводок по номеру операции и валюте
     *
     * @return список проводок
     */
    List<SprEntry> findAllBySprOperationIdAndCurrencyId(Long sprOperationId, Long currencyId) throws DAOException;
}
