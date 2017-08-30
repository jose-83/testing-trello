package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public final class MyLogger {
    public static Logger LOGGER;

    private static void setup() {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Handler consoleHandler = null;
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        if (handlers.length > 0 && handlers[0] instanceof ConsoleHandler) {
            consoleHandler = handlers[0];
        }

        try {
            FileHandler fileTxt = new FileHandler("log.txt", true);
            fileTxt.setFormatter(new MyFormatter());
            logger.addHandler(fileTxt);

            if (consoleHandler != null) {
                rootLogger.removeHandler(consoleHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER = logger;
    }

    public static Logger getInstance() {
        if (LOGGER == null) {
            MyLogger.setup();
        }
        return LOGGER;
    }

    public static class MyFormatter extends Formatter {

        public static StringBuilder formatThrowable(Throwable e) {
            StringBuilder stack = new StringBuilder();
            String s = e.getClass().getName();
            String message = e.getLocalizedMessage();
            stack.append((message != null) ? (s + ": " + message) : s);
            stack.append("\n");

            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                stack.append("\tat ").append(stackTraceElement).append("\n");
            }
            return stack;
        }

        @Override
        public String format(LogRecord record) {
            String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date(record.getMillis()));

            StringBuilder buf = new StringBuilder(1000)
                    .append(date)
                    .append(" ");

            if (record.getLevel().intValue() > Level.WARNING.intValue()) {
                buf.append("ERROR");
            } else if (record.getLevel().intValue() > Level.INFO.intValue()) {
                buf.append("WARN ");
            } else if (record.getLevel().intValue() > Level.CONFIG.intValue()) {
                buf.append("INFO ");
            } else if (record.getLevel().intValue() > Level.FINE.intValue()) {
                buf.append("CONFIG");
            } else {
                buf.append("DEBUG");
            }

            buf.append(" ~ ")
                    .append(formatMessage(record))
                    .append("\n");

            return buf.toString();

        }
    }
}
