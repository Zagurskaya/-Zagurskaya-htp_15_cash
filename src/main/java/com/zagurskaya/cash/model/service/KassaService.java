package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.Kassa;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface KassaService extends Service<Kassa> {
    /**
     * Update Kassa for outer Operations
     *
     * @param date           - date
     * @param dutiesId       - number duties
     * @param currencyId     - currency code
     * @param sum            - sum
     * @param sprOperationId - operation code
     * @return true on successful create
     * @throws ServiceException error during execution of logical blocks and actions
     */
    boolean updateKassaOuterOperation(Date date, Long dutiesId, Long currencyId, Double sum, Long sprOperationId) throws ServiceException;
    /**
     * Update Kassa for inner Operations
     *
     * @param date           - date
     * @param dutiesId       - number duties
     * @param currencyId     - currency code
     * @param sum            - sum
     * @param sprOperationId - operation code
     * @return true on successful create
     * @throws ServiceException error during execution of logical blocks and actions
     */
    boolean updateKassaInnerOperation(Date date, Long dutiesId, Long currencyId, Double sum, Long sprOperationId) throws ServiceException;

    /**
     * Return ballance by user and duties
     *
     * @param user   - user
     * @param duties - user duties
     * @return true on successful create
     * @throws ServiceException error during execution of logical blocks and actions
     */
    List<Kassa> getBalance(User user, Duties duties) throws ServiceException;

}
