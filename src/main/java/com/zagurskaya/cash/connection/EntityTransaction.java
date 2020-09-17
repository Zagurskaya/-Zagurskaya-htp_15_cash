package com.zagurskaya.cash.connection;

import com.zagurskaya.cash.dao.Dao;
import com.zagurskaya.cash.exception.DataBaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {

    static final Logger logger = LogManager.getLogger(EntityTransaction.class);
    private Connection connection;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public void init(Dao dao, Dao... daos) {
        if (connection == null) {
            try {
                connection = connectionPool.retrieve();
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during init connection", e);
                throw new DataBaseConnectionException("Database exception during init connection", e);
            }
            dao.setConnection(connection);
            for (Dao daoElement : daos) {
                daoElement.setConnection(connection);
            }
        }
    }

    public void end() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connectionPool.putBack(connection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during end connection", e);
                throw new DataBaseConnectionException("Database exception during end connection", e);
            }
            connection = null;
        }
    }

    public void commit() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during commit connection", e);
                throw new DataBaseConnectionException("Database exception during commit connection", e);
            }
        }
    }

    public void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during rollback connection", e);
                throw new DataBaseConnectionException("Database exception during rollback connection", e);
            }
            connection = null;
        }
    }
}
