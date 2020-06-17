import org.testng.TestNG;
import java.util.Arrays;
import java.util.List;


public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                "./src/test/resources/task-4/testng.xml", "./src/test/resources/task-4/testng-factory.xml",
                "./src/test/resources/task-4/testng-listener.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
