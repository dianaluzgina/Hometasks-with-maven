package stepdefs;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.DataProvider;

@CucumberOptions(features = "./src/test/resources/features")
public class TestRunner extends AbstractTestNGCucumberTests {

  @DataProvider
  @Override
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
