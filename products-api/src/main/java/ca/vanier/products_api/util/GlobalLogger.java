package ca.vanier.products_api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalLogger {

    private GlobalLogger() {
        // Prevent instantiation
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    public static void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    public static void error(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).error(message, throwable);
    }

    public static void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }
}
