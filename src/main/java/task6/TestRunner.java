package task6;

import org.testng.TestNG;

import java.util.Arrays;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                "./src/main/resources/task6/testng.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
