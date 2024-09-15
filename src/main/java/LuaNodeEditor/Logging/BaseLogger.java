package LuaNodeEditor.Logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseLogger {

    private static final Logger logger = Logger.getLogger(BaseLogger.class.getName());

    public static void logError(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }
}
