package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.RateNB;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface RateNBService extends Service<RateNB> {
    /**
     * Create List of rateNB
     *
     * @param rateNBList - List of rateNB
     * @return true on successful createCheckEn
     * @throws ServiceException error during execution of logical blocks and actions
     * @throws CommandException volition error
     */
    void create(List<RateNB> rateNBList) throws ServiceException, CommandException;
}
