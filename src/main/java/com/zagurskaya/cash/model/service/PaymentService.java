package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.DaoConstraintViolationException;
import com.zagurskaya.cash.exception.DaoException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    /**
     * Get operation list
     *
     * @return operation list
     * @throws ServiceException error during execution of logical blocks and actions
     */
    List<SprOperation> findAllSprOperation() throws ServiceException;

    /**
     * Get operation by Id
     *
     * @return operation
     * @throws ServiceException error during execution of logical blocks and actions
     */
    SprOperation findSprOperationById(Long id) throws ServiceException;

    /**
     * Count of rows in the UserOperations table
     *
     * @return Count of rows
     * @throws ServiceException error during execution of logical blocks and actions
     */
    int countRowsUserOperations(User user, Duties duties) throws ServiceException;

    /**
     * Get a list of user operations on the page
     *
     * @param user   - user
     * @param duties - user duties
     * @param page   - number page
     * @return list of Objects
     * @throws ServiceException error during execution of logical blocks and actions
     */
    List<UserOperation> onePartOfListUserOperationsOnPage(User user, Duties duties, int page) throws ServiceException;

    /**
     * Implement payment receiving money
     *
     * @param map           - code and values by each currency
     * @param specification - specification by operation
     * @param user          - user
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void implementPayment1000(Map<Long, Double> map, String specification, User user) throws ServiceException;

    /**
     * Implement payment transmitting money
     *
     * @param map           - code and values by each currency
     * @param specification - specification by operation
     * @param user          - user
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void implementPayment1100(Map<Long, Double> map, String specification, User user) throws ServiceException;

    /**
     * Implement payment buying currency
     *
     * @param map           - code and values by each currency
     * @param rate          - operation rate
     * @param specification - specification by operation
     * @param user          - user
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void implementPayment10(Map<Long, Double> map, Double rate, String specification, User user) throws ServiceException;

    /**
     * Implement payment selling currency
     *
     * @param map           - code and values by each currency
     * @param rate          - operation rate
     * @param specification - specification by operation
     * @param user          - user
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void implementPayment20(Map<Long, Double> map, Double rate, String specification, User user) throws ServiceException;

    /**
     * Implement payment communal payment
     *
     * @param map           - code and values by each currency
     * @param rate          - operation rate
     * @param specification - specification by operation
     * @param user          - user
     * @throws ServiceException error during execution of logical blocks and actions
     */
    void implementPayment998(Map<Long, Double> map, Double rate, String specification, User user) throws ServiceException;
}
