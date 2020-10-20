package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

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

    /**
     * Количество строк в таблите объекта
     *
     * @return количество строк
     * @throws ServiceException ошибке во время выполнения логическтх блоков и действий.
     */
    int countRows() throws ServiceException;

    /**
     * Получение списка объектов на определенной странице
     *
     * @param page - номер страницы
     * @return список пользователей
     */
    List<T> onePartOfListOnPage(int page) throws ServiceException;
}
