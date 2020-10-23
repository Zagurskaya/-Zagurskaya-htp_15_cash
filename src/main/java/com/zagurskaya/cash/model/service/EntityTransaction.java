package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.model.dao.Dao;
import com.zagurskaya.cash.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction management
 */
public class EntityTransaction {

    private static final Logger logger = LogManager.getLogger(EntityTransaction.class);
    private Connection connection;

    /**
     * Transfer a connection to one Dao
     *
     * @param dao - Dao
     */
    public void initSingleQuery(Dao dao) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().retrieve();
            dao.setConnection(connection);
        }
    }

    /**
     * Revert a connection to a connection pool with one Dao
     */
    public void endSingleQuery() {
        if (connection != null) {
            ConnectionPool.getInstance().putBack(connection);
            connection = null;
        }
    }

    /**
     * Transferring a connection to one or more Dao
     *
     * @param dao - Dao
     */
    public void init(Dao dao, Dao... daos) {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().retrieve();
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

    /**
     * Revert a connection to a connection pool with one or more Dao
     */
    public void end() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                ConnectionPool.getInstance().putBack(connection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Database exception during end pool", e);
                throw new RuntimeException("Database exception during end pool", e);
            }
            connection = null;
        }
    }

    /**
     * Make a transaction
     */
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

    /**
     * Cancel transaction
     */
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
