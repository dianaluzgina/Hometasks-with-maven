package task11.tests;

import org.testng.annotations.AfterClass;
import task11.entities.Browser;
import task11.logger.Log;

public class BaseTest {

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    Log.logInfo("Test finished");
    Browser.getInstance().closeBrowser();
  }

}
