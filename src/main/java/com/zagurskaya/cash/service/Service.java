package com.zagurskaya.cash.service;

import com.zagurskaya.cash.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    List<T> getAll() throws ServiceException;

    T getById(Long id) throws ServiceException;

    boolean create(T t) throws ServiceException;

    T read(long id) throws ServiceException;

    boolean update(T t) throws ServiceException;

    boolean delete(T t) throws ServiceException;

    List<T> getAll(String where) throws ServiceException;
}
