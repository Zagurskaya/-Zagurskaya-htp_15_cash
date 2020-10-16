package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;

import java.util.List;

public interface DutiesDao extends Dao<Duties> {
    /**
     * Получение списка открытых смен пользователя
     *
     * @return список смен
     */
    List<Duties> openDutiesUserToday(Long userId, String today) throws DAOException;

    /**
     * Получение открытой смены пользователя
     *
     * @return номер смены
     */
    Integer numberDutiesToday(User user, String today) throws DAOException;

    /**
     * Создание смены  и возврат ее с Id
     *
     * @param duties - смена
     * @return true при успешном создании
     */
    Long createAndReturnDutiesId(Duties duties) throws RepositoryConstraintViolationException, DAOException;
}
