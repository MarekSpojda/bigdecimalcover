import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("JUnit5Platform")
@RunWith(JUnitPlatform.class)
@SelectPackages("")
public class RunAllTests {
    private static final File reportFile = new File("junitError.log");

    public static void logTestInfo(String message) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile, true));
            bufferedWriter.write(message + System.lineSeparator());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}