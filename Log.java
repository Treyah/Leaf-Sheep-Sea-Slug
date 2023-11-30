import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collections;

/**
 * Simplifies the logging process by offering methods for
 * different types of log messages (e.g., general messages and errors).
 * The logged messages are saved to a file named Log.txt in the same directory.

 * @author Kelcey Calderon
 */
public class Log {

    /** The path to the log file. */
    private static final Path LOG_FILE_PATH = Paths.get("Log.txt");

    /**
     * Logs a general message.

     * @param message The message to be logged.
     */
    public static void msg(String message) {
        writeLog("INFO", message);
    }

    /**
     * Logs an error message.

     * @param message The error message to be logged.
     */
    public static void error(String message) {
        writeLog("ERROR", message);
    }

    /**
     * Writes a log message to both the console and the log file.
     * The log message is prefixed with a timestamp and the message type.
     * @param msgType The type of the message, e.g., INFO, ERROR.
     * @param message The actual message to be logged.
     */
    private static void writeLog(String msgType, String message) {
        System.out.println(message);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String formattedMessage = String.format("[%s] [%s]: %s", timestamp, msgType, message);

        // https://stackoverflow.com/questions/22859453/what-is-the-simplest-way-to-write-a-text-file-in-java
        try {
            if (!Files.exists(LOG_FILE_PATH)) {
                Files.createFile(LOG_FILE_PATH);
            }

            Files.write(LOG_FILE_PATH, Collections.singletonList(formattedMessage), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

}


