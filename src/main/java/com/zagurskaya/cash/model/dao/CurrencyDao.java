package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface CurrencyDao extends Dao<Currency> {
    /**
     * Get a list of currencies
     *
     * @return list of currencies
     * @throws DaoException database access error or other errors
     */
    List<Currency> findAll() throws DaoException;
    /**
     * Get a  list of SKV currencies
     *
     * @return list of SKV currencies
     * @throws DaoException database access error or other errors
     */
    List findAllSKV() throws DaoException;
}
