package Java9;

public class Private_Static_methods_java9 {
    public static void main(String []args) {
        LogOracle2 log = new LogOracle2();
        log.logInfo("");
        log.logWarn("");
        log.logError("");
        log.logFatal("");

        LogMySql2 log1 = new LogMySql2();
        log1.logInfo("");
        log1.logWarn("");
        log1.logError("");
        log1.logFatal("");
    }
}
final class LogOracle2 implements Logging2 {
}
final class LogMySql2 implements Logging2 {
}
interface Logging2 {
    String ORACLE = "Oracle_Database";
    String MYSQL = "MySql_Database";

    private void log(String message, String prefix) {
        getConnection();
        System.out.println("Log Message : " + prefix);
        closeConnection();
    }
    default void logInfo(String message) {
        log(message, "INFO");
    }
    default void logWarn(String message) {
        log(message, "WARN");
    }
    default void logError(String message) {
        log(message, "ERROR");
    }
    default void logFatal(String message) {
        log(message, "FATAL");
    }
    private static void getConnection() {
        System.out.println("Open Database connection");
    }
    private static void closeConnection() {
        System.out.println("Close Database connection");
    }
}