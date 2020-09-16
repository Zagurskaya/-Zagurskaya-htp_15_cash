package com.gmail.zagurskaya.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    boolean create(T t) throws SQLException;

    T read(long id) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    List<T> getAll(String where) throws SQLException;

}
