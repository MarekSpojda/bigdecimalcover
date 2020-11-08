import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.engine.TestExecutionResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalCoverTest {
    @AfterEach
    void after(TestInfo testInfo) {
        RunAllTests.logTestInfo(testInfo.toString());
    }

    @Test
    public void addTest() {
        BigDecimalCover bigDecimalCover = new BigDecimalCover(3);

        assertEquals("10.000", bigDecimalCover.add("4", "6"));
        assertEquals("10.000", bigDecimalCover.add("3.337", "6.6635"));
        assertEquals("0.000", bigDecimalCover.add("3.337", "-3.337"));
        assertEquals("0.000", bigDecimalCover.add("0.37", "-0.3704"));
        assertEquals("0.000", bigDecimalCover.add("0", "0.00"));

        bigDecimalCover = new BigDecimalCover(0);

        assertEquals("0", bigDecimalCover.add("-2", "2.2"));
        assertEquals("3", bigDecimalCover.add("0.458904", "2.541096"));
    }

    @Test
    public void subtractTest() {
        BigDecimalCover bigDecimalCover = new BigDecimalCover(5);
        assertEquals("1.23456", bigDecimalCover.subtract("3.23456", "2"));
    }
}
