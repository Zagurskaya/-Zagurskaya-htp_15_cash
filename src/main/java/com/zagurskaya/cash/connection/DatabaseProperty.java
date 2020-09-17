package com.zagurskaya.cash.connection;

import com.zagurskaya.cash.exception.DataBaseConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperty {
    static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static DatabaseProperty instance;
    private static Properties properties;


    private DatabaseProperty() {
    }

    public static DatabaseProperty getInstance() {
        if (instance == null) {
            instance = new DatabaseProperty();
            return instance;
        }
        return instance;
    }

    public Properties getProperties() {
        return readProperties("database.properties");
    }

    private Properties readProperties(String propertiesFilePath) {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {

            if (input == null) {
                logger.log(Level.ERROR, "no properties file found");
                throw new DataBaseConnectionException("no properties file found");
            }

            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            logger.log(Level.ERROR, "no properties file found");
            throw new DataBaseConnectionException("no properties file found");
        }
    }

}
