package com.zagurskaya.cash.model.pool;

import com.zagurskaya.cash.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private LinkedBlockingQueue<ProxyConnection> availableConnection;
    private LinkedBlockingQueue<ProxyConnection> usedConnection;

    private static final int MIN_POOL_SIZE = 20;
    private static final String DRIVER = DatabaseProperty.getInstance().getDriver();
    private static final String URL = DatabaseProperty.getInstance().getUrl();
    private static final String USER = DatabaseProperty.getInstance().getUser();
    private static final String PASSWORD = DatabaseProperty.getInstance().getPassword();

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Driver not registered", e);
            throw new RuntimeException("Driver not registered", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ConnectionPool() {
        availableConnection = new LinkedBlockingQueue<>(MIN_POOL_SIZE);
        usedConnection = new LinkedBlockingQueue<>(MIN_POOL_SIZE);
        for (int i = 0; i < MIN_POOL_SIZE; i++) {
            availableConnection.offer(createConnection());
        }
    }

    //перенести
    //todo
    private ProxyConnection createConnection() {
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

    public Connection retrieve() {
        ProxyConnection newConnection = null;
        try {
            newConnection = availableConnection.take();
            usedConnection.put(newConnection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Connection not found", e);
            Thread.currentThread().interrupt();
        }
        return newConnection;
    }

    public void putBack(Connection connection) {
        if (connection != null) {
            if (connection instanceof ProxyConnection && usedConnection.remove(connection)) {
                try {
                    availableConnection.put((ProxyConnection) connection);
                } catch (InterruptedException e) {
                    logger.log(Level.ERROR, "Connection not found", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void destroyPoll() {
        for (int i = 0; i < availableConnection.size(); i++) {
            try {
                ProxyConnection connection = availableConnection.take();
                connection.closeConnection();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Exception during destroy poll", e);
                throw new RuntimeException("Exception during destroy poll", e);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Exception during destroy poll", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Exception during deregister Drivers", e);
                throw new RuntimeException("Exception during deregister Drivers", e);
            }
        }
    }

}
