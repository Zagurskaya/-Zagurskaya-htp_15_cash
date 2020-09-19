package com.zagurskaya.cash.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperty {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static DatabaseProperty instance;
    private static Properties properties;

    private static final String PROPERTY_NAME = "database.properties";
    private static final String DRIVER = "database.driver.name";
    private static final String URL = "database.url";
    private static final String USER = "database.username";
    private static final String PASSWORD = "database.password";

    private DatabaseProperty() {
    }

    public static DatabaseProperty getInstance() {
        if (instance == null) {
            instance = new DatabaseProperty();
            createProperties();
            return instance;
        }
        return instance;
    }

    public String getDriver() {
        return properties.getProperty(DRIVER);
    }

    public String getUrl() {
        return properties.getProperty(URL);
    }

    public String getUser() {
        return properties.getProperty(USER);
    }

    public String getPassword() {
        return properties.getProperty(PASSWORD);
    }

    private static void createProperties() {
        properties = readProperties(PROPERTY_NAME);
    }

    private static Properties readProperties(String propertiesFilePath) {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {

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
