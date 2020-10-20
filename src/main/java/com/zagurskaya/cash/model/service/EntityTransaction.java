package com.zagurskaya.cash.model.service;

import com.zagurskaya.cash.model.dao.Dao;
import com.zagurskaya.cash.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Управленеи транзакциями
 */
public class EntityTransaction {

    private static final Logger logger = LogManager.getLogger(EntityTransaction.class);
    private Connection connection;

    /**
     * Передача соединения в одно Dao
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
     * Возврат соединения в пул соединений с одним Dao
     */
    public void endSingleQuery() {
        if (connection != null) {
            ConnectionPool.getInstance().putBack(connection);
            connection = null;
        }
    }

    /**
     * Передача соединения в одно или больше Dao
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
     * Возврат соединения в пул соединений с одно или более Dao
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
     * Совершить транзакцию
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
     * Отмена транзакцию
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
