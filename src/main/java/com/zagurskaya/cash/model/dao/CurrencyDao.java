package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface CurrencyDao extends Dao<Currency> {
    /**
     * Получение списка валют
     *
     * @return список объектов
     */
    List<Currency> findAll() throws DaoException;

}
