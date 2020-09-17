package com.gmail.zagurskaya.connection;

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
    private static final int MAX_POOL_SIZE = 30;

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://127.0.0.1:2016/zagurskaya" +
            "?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "";

    static final int initConnectionCount = 30;

    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Driver not registered", e);
            throw new RuntimeException("Driver not registered", e);
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
            throw new RuntimeException("Driver not registered", e);
        }
        return connection;
    }

    public synchronized Connection retrieve() {
        Connection newConnection = null;
        try {
            newConnection = availableConnection.take();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Connection not found", e);
            throw new RuntimeException("Connection not found", e);
        }
        usedConnection.add(newConnection);
        return newConnection;
    }

    public synchronized void putBack(Connection connection) throws NullPointerException {
        if (connection != null) {
            if (usedConnection.remove(connection)) {
                availableConnection.add(connection);
            } else {
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
                throw new RuntimeException("Exception during destroy poll", e);
            }
        }
    }
}
