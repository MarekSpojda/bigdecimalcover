import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ExampleNumbers {
    public static String getBin5K() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Stream<String> fileStream =
                    Files.lines(Paths.get("bin5K.txt"), StandardCharsets.UTF_8);
            fileStream.forEach(stringBuilder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getBin1K() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Stream<String> fileStream =
                    Files.lines(Paths.get("bin1K.txt"), StandardCharsets.UTF_8);
            fileStream.forEach(stringBuilder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}