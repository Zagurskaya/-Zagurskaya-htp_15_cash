package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.exception.DaoException;

import java.sql.Date;

public interface RateNBDao extends Dao<RateNB> {
    /**
     * Find the latest rate NB by currency
     *
     * @return rate NB
     * @throws DaoException database access error or other errors
     */
    RateNB rateNBToday(Date date, Long currencyId) throws DaoException;
}
