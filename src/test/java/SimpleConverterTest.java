import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FailedTestWatcher.class)
public class SimpleConverterTest {
    @Test
    public void decToBinTest() {
        SimpleConverter simpleConverter = new SimpleConverter();
        assertEquals("1010", simpleConverter.decToBin("10"));
    }
}
