package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

public interface RateCBService extends Service<RateCB> {

    /**
     * Get the rate CB by values coming/spending
     *
     * @param now      - date and time of the rate
     * @param coming   - code currency by the coming
     * @param spending - code currency by the spending
     * @return true on successful creation
     * @throws ServiceException error during execution of logical blocks and actions
     */
    Double rateCBToday(LocalDateTime now, Long coming, Long spending) throws ServiceException;

    /**
     * Create List of rateCB
     *
     * @param rateCBList - List of rateCB
     * @return true on successful createCheckEn
     * @throws ServiceException error during execution of logical blocks and actions
     * @throws CommandException volition error
     */
    void create(List<RateCB> rateCBList) throws ServiceException, CommandException;
}
