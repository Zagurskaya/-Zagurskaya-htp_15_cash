package com.zagurskaya.cash.util;

import com.zagurskaya.cash.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Data property
 */
public class DataProperty {
    private static final Logger logger = LogManager.getLogger(DataProperty.class);
    private static Properties properties;

    private static final String PROPERTY_NAME = "installation.properties";
    public static String checkPatch;
    public static String outPatch;

    static {
        properties = readProperties();
        checkPatch = properties.getProperty("setup.checkpatch");
        outPatch = properties.getProperty("setup.outpatch");
    }

    private DataProperty() {
    }

    private static Properties readProperties() {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTY_NAME)) {

            if (input == null) {
                logger.log(Level.ERROR, "no properties data file found");
                throw new RuntimeException("no properties data file found");
            }
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            logger.log(Level.ERROR, "no properties data file found");
            throw new RuntimeException("no properties data file found");
        }
    }
}
