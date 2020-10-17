package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface CurrencyService extends Service<Currency> {
    /**
     * Получение списка валют
     *
     * @return список валют
     */
    List findAll() throws ServiceException;

}
