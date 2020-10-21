package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.entity.Duties;
import com.zagurskaya.cash.entity.SprOperation;
import com.zagurskaya.cash.entity.User;
import com.zagurskaya.cash.entity.UserOperation;
import com.zagurskaya.cash.exception.CommandException;
import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    /**
     * Получение списка операций
     *
     * @return список операций
     */
    List<SprOperation> findAllSprOperation() throws ServiceException;

    /**
     * Получение операции
     *
     * @return операция
     */
    SprOperation findSprOperationById(Long id) throws ServiceException;

    /**
     * Выполнение операции 1000
     */
    void implementPayment1000(Map<Long, Double> map, String specification, User user) throws ServiceException;

    int countRowsUserOperations(User user, Duties duties) throws ServiceException;

    List<UserOperation> onePartOfListUserOperationsOnPage(User user, Duties duties, int page) throws ServiceException;
}
