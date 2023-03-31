import java.util.Date;

public class TestLogFileInitializer {
    public static void main(String[] args) {
        TestLogger.logTestInfo("", true);
        TestLogger.logTestInfo("Failed tests list - " + new Date().toString(), true);
        TestLogger.logTestInfo("-----------------", true);
    }
}