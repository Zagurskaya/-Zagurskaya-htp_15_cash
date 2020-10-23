package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.SprEntry;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface SprEntryDao extends Dao<SprEntry> {
    /**
     * Find a list of entries
     *
     * @return list of entries
     * @throws DaoException database access error or other errors
     */
    List<SprEntry> findAll() throws DaoException;

    /**
     * Find a list of entries by operation and currency
     *
     * @return list of entries
     * @throws DaoException database access error or other errors
     */
    List<SprEntry> findAllBySprOperationIdAndCurrencyId(Long sprOperationId, Long currencyId) throws DaoException;
}
