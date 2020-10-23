package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Currency;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface CurrencyService extends Service<Currency> {
    /**
     * Get a list of currencies
     *
     * @return currency list
     * @throws ServiceException error during execution of logical blocks and actions
     */
    List findAll() throws ServiceException;

}
