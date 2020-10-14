package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.exception.DAOException;
import com.zagurskaya.cash.exception.RepositoryConstraintViolationException;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
    /**
     * Получение списка объектов начиная с startPosition позиции в количестве <= limit
     *
     * @param limit         - количество
     * @param startPosition - начальная позиция
     * @return список объектов
     */
    List<T> findAll(int limit, int startPosition) throws DAOException;

    /**
     * Поиск объекта по ID
     *
     * @param id - ID
     * @return объект
     */
    T findById(Long id) throws DAOException;

    /**
     * Создание объекта
     *
     * @param t - объект
     * @return true при успешном создании
     */
    boolean create(T t) throws RepositoryConstraintViolationException, DAOException;

    /**
     * Изменение объекта
     *
     * @param t - объект
     * @return true при успешном изменении
     */
    boolean update(T t) throws RepositoryConstraintViolationException, DAOException;

    /**
     * Удаление объекта
     *
     * @param t - объект
     * @return true при успешном удаление
     */
    boolean delete(T t) throws DAOException;

    /**
     * Установление соединения
     *
     * @param connection - соединение
     */
    void setConnection(Connection connection);
}
