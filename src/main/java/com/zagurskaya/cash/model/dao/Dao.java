package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;

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
    List<T> findAll(int limit, int startPosition) throws DaoException;

    /**
     * Поиск объекта по ID
     *
     * @param id - ID
     * @return объект
     */
    T findById(Long id) throws DaoException;

    /**
     * Создание объекта
     *
     * @param t - объект
     * @return true при успешном создании
     */
    Long create(T t) throws DaoConstraintViolationException, DaoException;

    /**
     * Изменение объекта
     *
     * @param t - объект
     * @return true при успешном изменении
     */
    boolean update(T t) throws DaoConstraintViolationException, DaoException;

    /**
     * Удаление объекта
     *
     * @param t - объект
     * @return true при успешном удаление
     */
    boolean delete(T t) throws DaoException;

    /**
     * Количество строк в таблице объекта
     *
     * @return количество строк
     * @throws DaoException ошибке доступа к базе данных или других ошибках.
     */
    int countRows() throws DaoException;

    /**
     * Установление соединения
     *
     * @param connection - соединение
     */
    void setConnection(Connection connection);
}
