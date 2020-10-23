package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DaoException;

import java.util.List;

public interface DutiesDao extends Dao<Duties> {
    /**
     * Get a list of open user duties
     *
     * @return list of user duties
     * @throws DaoException database access error or other errors
     */
    List<Duties> openDutiesUserToday(Long userId, String today) throws DaoException;

    /**
     * Get number of user duties
     *
     * @return duties number
     * @throws DaoException database access error or other errors
     */
    Integer numberDutiesToday(User user, String today) throws DaoException;

}
