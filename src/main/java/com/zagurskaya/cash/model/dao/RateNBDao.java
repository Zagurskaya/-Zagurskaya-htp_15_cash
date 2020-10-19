package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.exception.DaoException;

import java.sql.Date;

public interface RateNBDao extends Dao<RateNB> {
    /**
     * Получение последнего курса НБ по валюте
     *
     * @return курс НБ
     */
    RateNB rateNBToday(Date date, Long currencyId) throws DaoException;
}
