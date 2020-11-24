package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.RateCB;
import com.zagurskaya.cash.exception.DaoException;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface RateCBDao extends Dao<RateCB> {
    /**
     * Get the rate CB by values coming/spending
     *
     * @param now      - date and time of the rate
     * @param coming   - code currency by the coming
     * @param spending - code currency by the spending
     * @return true on successful creation
     * @throws DaoException database access error or other errors
     */
    BigDecimal rateCBToday(LocalDateTime now, Long coming, Long spending) throws DaoException;
}
