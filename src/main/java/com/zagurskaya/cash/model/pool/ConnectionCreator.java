package com.zagurskaya.cash.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create a database connection
 */
class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger(ConnectionCreator.class);

    private static final String URL = DatabaseProperty.url;
    private static final String USER = DatabaseProperty.user;
    private static final String PASSWORD = DatabaseProperty.password;

    /**
     * Get connection
     *
     * @return connection
     */
    static ProxyConnection create() {
        Connection connection;
        ProxyConnection proxyConnection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            proxyConnection = new ProxyConnection(connection);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Driver not registered", e);
            throw new RuntimeException("Driver not registered", e);
        }
        return proxyConnection;
    }


}
