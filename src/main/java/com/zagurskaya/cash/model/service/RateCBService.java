package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.ServiceException;

import java.sql.Timestamp;

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
    Double rateCBToday(Timestamp now, Long coming, Long spending) throws ServiceException;
}
