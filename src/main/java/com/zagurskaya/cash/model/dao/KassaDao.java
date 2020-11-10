package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface KassaDao extends Dao<Kassa> {

    /**
     * Find a record from the kassa by currency, date and duties
     *
     * @return record from the kassa
     * @throws DaoException database access error or other errors
     */
    Kassa findByCurrencyIdAndDateAndDutiesId(LocalDate date, Long dutiesId, Long currencyId) throws DaoException;

    /**
     * Find a record from the kassa by user and duties
     *
     * @return record from the kassa
     * @throws DaoException database access error or other errors
     */
    List<Kassa> findAllByUserIdAndDutiesId(Long userId, Long dutiesId) throws DaoException;
}
