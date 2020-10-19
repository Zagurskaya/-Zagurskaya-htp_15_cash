package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;

import java.util.List;

public interface DutiesDao extends Dao<Duties> {
    /**
     * Получение списка открытых смен пользователя
     *
     * @return список смен
     */
    List<Duties> openDutiesUserToday(Long userId, String today) throws DaoException;

    /**
     * Получение открытой смены пользователя
     *
     * @return номер смены
     */
    Integer numberDutiesToday(User user, String today) throws DaoException;

    /**
     * Создание смены  и возврат ее с Id
     *
     * @param duties - смена
     * @return true при успешном создании
     */
    Long createAndReturnDutiesId(Duties duties) throws DaoConstraintViolationException, DaoException;
}
