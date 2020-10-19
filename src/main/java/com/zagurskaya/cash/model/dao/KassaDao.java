package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.exception.DaoException;

import java.sql.Date;
import java.util.List;

public interface KassaDao extends Dao<Kassa> {

    /**
     * Получение записи из картотеки kassa по валюте, дате и номеру смены
     *
     * @return запись из картотеки kassa
     */

    Kassa findByCurrencyIdAndDateAndDutiesId(Date date, Long dutiesId, Long currencyId) throws DaoException;

    List<Kassa> findAllByUserIdAndDutiesId(Long userId, Long dutiesId) throws DaoException;
}
