package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface DutiesService extends Service<Duties> {
    /**
     * Получение открытой смены пользователя
     *
     * @return смена
     */
    Duties openDutiesUserToday(User user, String today) throws ServiceException;

    /**
     * Открытие новой смены пользователя
     */
    void openNewDuties(User user) throws ServiceException, ServiceConstraintViolationException;

    /**
     * Закрытие смены пользователя
     */
    void closeOpenDutiesUserToday(User user) throws ServiceConstraintViolationException, ServiceException;

}
