package com.gmail.zagurskaya.dao;

import com.gmail.zagurskaya.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao<T> {
    List<T> getAll() throws DAOException;

    T getById(Long id) throws DAOException;

    boolean create(T t) throws DAOException;

    T read(long id) throws DAOException;

    boolean update(T t) throws DAOException;

    boolean delete(T t) throws DAOException;

//    boolean deleteById(Long id) throws DAOException;

    List<T> getAll(String where) throws DAOException;

    default void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    default void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}
