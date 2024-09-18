package LuaNodeEditor.Logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BaseLogger {

    private static final Logger logger = Logger.getLogger(BaseLogger.class.getName());
    private static boolean isLoggingEnabled = true;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String ORANGE = "\u001B[38;5;214m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[38;5;10m";

    private static final Level SUCCESS = new Level("SUCCESS", Level.INFO.intValue() + 1) {};

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(java.util.logging.LogRecord pRecord) {
                String color;
                String level = pRecord.getLevel().getName();
                if (pRecord.getLevel() == Level.SEVERE) {
                    color = RED;
                } else if (pRecord.getLevel() == Level.WARNING) {
                    color = ORANGE;
                } else if (pRecord.getLevel() == Level.INFO) {
                    color = BLUE;
                } else if (pRecord.getLevel() == SUCCESS) {
                    color = GREEN;
                } else {
                    color = RESET;
                }
                return color + level + ": " + pRecord.getMessage() + RESET + "\n";
            }
        });
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }

    public static void setLoggingEnabled(boolean pEnabled) {
        isLoggingEnabled = pEnabled;
    }

    public static void logError(String pMessage, Throwable pThrowable) {
        logger.log(Level.SEVERE, pMessage, pThrowable);
    }

    public static void logInfo(String pMessage) {
        if (isLoggingEnabled) {
            logger.log(Level.INFO, pMessage);
        }
    }

    public static void logWarning(String pMessage) {
        logger.log(Level.WARNING, pMessage);
    }

    public static void logSuccess(String pMessage) {
        if (isLoggingEnabled) {
            logger.log(SUCCESS, pMessage);
        }
    }
}
