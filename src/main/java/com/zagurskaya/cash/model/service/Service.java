package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    /**
     * Search Object by ID
     *
     * @param id - ID
     * @return Object
     * @throws ServiceException error during execution of logical blocks and actions
     */
    T findById(Long id) throws ServiceException;

    /**
     * Create Object
     *
     * @param t - Object
     * @return true on successful createCheckEn
     * @throws ServiceException error during execution of logical blocks and actions
     * @throws CommandException volition error
     */
    boolean create(T t) throws ServiceException, CommandException;

    /**
     * Update Object
     *
     * @param t - Object
     * @return true on successful update
     * @throws ServiceException error during execution of logical blocks and actions
     */
    boolean update(T t) throws ServiceException, CommandException;

    /**
     * Delete Object
     *
     * @param t - Object
     * @return true on successful delete
     * @throws ServiceException error during execution of logical blocks and actions
     */
    boolean delete(T t) throws ServiceException;

    /**
     * Count of rows in the object table
     *
     * @return Count of rows
     * @throws ServiceException error during execution of logical blocks and actions
     */
    int countRows() throws ServiceException;

    /**
     * Get a list of Objects on the page
     *
     * @param page - number page
     * @return list of Objects
     * @throws ServiceException error during execution of logical blocks and actions
     */
    List<T> onePartOfListOnPage(int page) throws ServiceException;
}
