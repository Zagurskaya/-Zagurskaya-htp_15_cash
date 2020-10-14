package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

public interface Service<T> {
    /**
     * Поиск объекта по ID
     *
     * @param id - ID
     * @return объект
     */
    T findById(Long id) throws ServiceException;

    /**
     * Создание объекта
     *
     * @param t - объект
     * @return true при успешном создании
     */
    boolean create(T t) throws ServiceException, ServiceConstraintViolationException;

    /**
     * Изменение объекта
     *
     * @param t - объект
     * @return true при успешном изменении
     */
    boolean update(T t) throws ServiceException, ServiceConstraintViolationException;

    /**
     * Удаление объекта
     *
     * @param t - объект
     * @return true при успешном удаление
     */
    boolean delete(T t) throws ServiceException;

}
