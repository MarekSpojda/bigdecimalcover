import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import java.io.*;

@SuppressWarnings("JUnit5Platform")
@RunWith(JUnitPlatform.class)
@SelectClasses(InitLogFileTest.class)
@SelectPackages("")
public class RunAllTests {
    private static final File reportFile = new File("junitError.log");

    public static void logTestInfo(String message, boolean append) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile, append));
            bufferedWriter.write(message + System.lineSeparator());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}