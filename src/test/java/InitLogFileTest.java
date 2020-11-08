import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitLogFileTest {
    @Test
    public void initLogFile() {
        RunAllTests.logTestInfo("Failed tests list", false);
        RunAllTests.logTestInfo("-----------------", true);
        assertTrue(true);
    }
}