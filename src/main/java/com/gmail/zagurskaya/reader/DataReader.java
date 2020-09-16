package com.gmail.zagurskaya.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class DataReader {
    static final Logger logger = LogManager.getLogger(DataReader.class);

    public String read(String fileName) {
        String text = "";
        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            text = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        } catch (IOException e) {
            logger.log(Level.ERROR, "Problems with the path to file", e);
        } catch (NullPointerException e) {
            logger.log(Level.ERROR, "Problems with the path to file", e);
        }
        return text;
    }
}
