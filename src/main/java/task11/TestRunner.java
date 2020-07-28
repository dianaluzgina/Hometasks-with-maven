package task11;

import org.testng.TestNG;
import task11.entities.Browser;

import java.util.Arrays;
import java.util.List;

public class TestRunner {

  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].contains("--browser=")) {
        Browser.browserType = args[i].split("=")[1];
      }
    }
    TestNG testNG = new TestNG();
    List<String> files = Arrays.asList(
        "./src/main/resources/task11/testng.xml");
    testNG.setTestSuites(files);
    testNG.run();
  }
}
