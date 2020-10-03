package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.exception.ServiceConstraintViolationException;
import com.zagurskaya.cash.exception.ServiceException;

public interface Service<T> {
//    List<T> findAll() throws ServiceException;

    T findById(Long id) throws ServiceException;

    boolean create(T t) throws ServiceException, ServiceConstraintViolationException;

    boolean update(T t) throws ServiceException, ServiceConstraintViolationException;

    boolean delete(T t) throws ServiceException;

}
