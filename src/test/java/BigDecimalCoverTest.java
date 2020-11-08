import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FailedTestWatcher.class)
public class BigDecimalCoverTest {
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
