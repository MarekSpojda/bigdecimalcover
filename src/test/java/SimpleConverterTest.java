import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleConverterTest {
    @AfterEach
    void after(TestInfo testInfo) {
        RunAllTests.logTestInfo(testInfo.toString());
    }

    @Test
    public void decToBinTest() {
        SimpleConverter simpleConverter = new SimpleConverter();
        assertEquals("1010", simpleConverter.decToBin("10"));
    }
}
