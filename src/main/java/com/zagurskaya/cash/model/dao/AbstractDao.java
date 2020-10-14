package com.zagurskaya.cash.model.dao;

import java.sql.Connection;

public abstract class AbstractDao {

    protected Connection connection;

    /**
     * Установление соединения
     *
     * @param connection - соединение
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
