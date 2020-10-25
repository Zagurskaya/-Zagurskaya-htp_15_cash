package com.zagurskaya.cash.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Database property
 */
class DatabaseProperty {
    private static final Logger logger = LogManager.getLogger(DatabaseProperty.class);
    private static Properties properties;

    private static final String PROPERTY_NAME = "database.properties";
    static String driver;
    static String url;
    static String user;
    static String password;

    static {
        properties = readProperties();
        driver = properties.getProperty("database.driver.name");
        url = properties.getProperty("database.url");
        user = properties.getProperty("database.username");
        password = properties.getProperty("database.password");
    }

    private DatabaseProperty() {
    }

    private static Properties readProperties() {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTY_NAME)) {

            if (input == null) {
                logger.log(Level.ERROR, "no properties file found");
                throw new RuntimeException("no properties file found");
            }
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            logger.log(Level.ERROR, "no properties file found");
            throw new RuntimeException("no properties file found");
        }
    }
}
