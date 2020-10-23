package com.zagurskaya.cash.model.dao;

import java.sql.Connection;

public abstract class AbstractDao {

    protected Connection connection;

    /**
     * Establishing a connection
     *
     * @param connection - connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
