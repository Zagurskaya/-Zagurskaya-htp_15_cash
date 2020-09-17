package com.gmail.zagurskaya.connection;

import com.gmail.zagurskaya.exception.DataBaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private LinkedBlockingQueue<Connection> availableConnection;
    private ArrayDeque<Connection> usedConnection;
    private static final int MIN_POOL_SIZE = 20;

    private static final String DRIVER = DatabaseProperty.getInstance().getProperties().getProperty("database.driver.name");
    private static final String URL = DatabaseProperty.getInstance().getProperties().getProperty("database.url");
    private static final String USER = DatabaseProperty.getInstance().getProperties().getProperty("database.username");
    private static final String PASSWORD = DatabaseProperty.getInstance().getProperties().getProperty("database.password");

    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Driver not registered", e);
            throw new DataBaseConnectionException("Driver not registered", e);
        }
    }

    public static ConnectionPool getInstance() {
        try {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public ConnectionPool() {
        availableConnection = new LinkedBlockingQueue<>(MIN_POOL_SIZE);
        usedConnection = new ArrayDeque<>(MIN_POOL_SIZE);
        for (int i = 0; i < MIN_POOL_SIZE; i++) {
            availableConnection.offer(createConnection());
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Driver not registered", e);
            throw new DataBaseConnectionException("Driver not registered", e);
        }
        return connection;
    }

    public synchronized Connection retrieve() {
        Connection newConnection = null;
        try {
            newConnection = availableConnection.take();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Connection not found", e);
            throw new DataBaseConnectionException("Connection not found", e);
        }
        usedConnection.add(newConnection);
        return newConnection;
    }

    public synchronized void putBack(Connection connection) throws NullPointerException {
        if (connection != null) {
            if (usedConnection.remove(connection)) {
                availableConnection.add(connection);
            } else {
                logger.log(Level.ERROR, "Connection not in the usedConnection array");
                throw new NullPointerException("Connection not in the usedConnection array");
            }
        }
    }

    public void destroyPoll() {
        for (int i = 0; i < availableConnection.size(); i++) {
            try {
                Connection connection = availableConnection.take();
                connection.close();
            } catch (Exception e) {
                logger.log(Level.ERROR, "Exception during destroy poll", e);
                throw new DataBaseConnectionException("Exception during destroy poll", e);
            }
        }
    }

}
