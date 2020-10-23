package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
    /**
     * Get a list of Objects starting from the startPosition position in the count of <= limit
     *
     * @param limit         - count
     * @param startPosition - start position
     * @return list of Objects
     * @throws DaoException database access error or other errors.
     */
    List<T> findAll(int limit, int startPosition) throws DaoException;

    /**
     * Object search by ID
     *
     * @param id - ID
     * @return object
     * @throws DaoException database access error or other errors.
     */
    T findById(Long id) throws DaoException;

    /**
     * Create Object
     *
     * @param t - Object
     * @return true on successful creation
     * @throws DaoException                    database access error or other errors
     * @throws DaoConstraintViolationException duplication data
     */
    Long create(T t) throws DaoConstraintViolationException, DaoException;

    /**
     * Update Object
     *
     * @param t - Object
     * @return true on successful change
     * @throws DaoException                    database access error or other errors
     * @throws DaoConstraintViolationException duplication data
     */
    boolean update(T t) throws DaoConstraintViolationException, DaoException;

    /**
     * Delete Object
     *
     * @param t - Object
     * @return true on successful delete
     * @throws DaoException database access error or other errors.
     */
    boolean delete(T t) throws DaoException;

    /**
     * Count of rows in the object table
     *
     * @return Count of rows
     * @throws DaoException database access error or other errors.
     */
    int countRows() throws DaoException;

    /**
     * Establishing a connection
     *
     * @param connection - connection
     */
    void setConnection(Connection connection);
}
