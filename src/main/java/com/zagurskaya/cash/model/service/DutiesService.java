package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.ServiceException;

public interface DutiesService extends Service<Duties> {
    /**
     * Get the open duties of user
     *
     * @return duties
     * @throws ServiceException error during execution of logical blocks and actions
     */
    Duties openDutiesUserToday(User user, String today) throws ServiceException;

    /**
     * Open a new duties of user
     *
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void openNewDuties(User user) throws ServiceException;

    /**
     * Close the user duties
     *
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void closeOpenDutiesUserToday(User user) throws ServiceException;

}
