import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FailedTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Class<?>> optionalClass = context.getTestClass();
        String className = "";
        if (optionalClass.isPresent()) {
            className = optionalClass.get().getName();
        }
        String testName = context.getDisplayName().replace("(", "").replace(")", "");
        RunAllTests.logTestInfo(className + " : " +
                context.getDisplayName(), true);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        cause.printStackTrace(printWriter);
        RunAllTests.logTestInfo(
                getLine(className, testName, stringWriter.toString()) + System.lineSeparator(),
                true);
    }

    private String getLine(String className, String testName, String textToSearch) {
        String lineRegex =
                "at " + className + "\\." + testName + "\\(" + className + "\\.java:.+\\)";
        Pattern linePattern = Pattern.compile(lineRegex);
        Matcher lineMatcher = linePattern.matcher(textToSearch);
        if (lineMatcher.find()) {
            return lineMatcher.group();
        }
        return "(unable to get failed test info)";
    }
}