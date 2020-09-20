package com.zagurskaya.cash.model.pool;

import com.zagurskaya.cash.model.dao.Dao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {

    private static final Logger logger = LogManager.getLogger(EntityTransaction.class);
    private Connection connection;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public void init(Dao dao, Dao... daos) {
        if (connection == null) {
            try {
                connection = connectionPool.retrieve();
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during init pool", e);
                throw new RuntimeException("Database exception during init pool", e);
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
                logger.log(Level.ERROR, "Database exception during end pool", e);
                throw new RuntimeException("Database exception during end pool", e);
            }
            connection = null;
        }
    }

    public void commit() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during commit pool", e);
                throw new RuntimeException("Database exception during commit pool", e);
            }
        }
    }

    public void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during rollback pool", e);
                throw new RuntimeException("Database exception during rollback pool", e);
            }
        }
    }
}
