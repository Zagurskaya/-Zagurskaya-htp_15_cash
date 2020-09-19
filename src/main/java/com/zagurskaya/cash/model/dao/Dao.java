package com.zagurskaya.cash.model.dao;

import com.zagurskaya.cash.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
    List<T> getAll() throws DAOException;

    T getById(Long id) throws DAOException;

    boolean create(T t) throws DAOException;

    T read(long id) throws DAOException;

    boolean update(T t) throws DAOException;

    boolean delete(T t) throws DAOException;

    List<T> getAll(String where) throws DAOException;

    void setConnection(Connection connection);
}
