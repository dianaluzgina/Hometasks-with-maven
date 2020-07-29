package task11.entities;


import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import task11.logger.Log;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {

  private static final String PATTERN_PATH_TO_SCREENSHOT_FILE = "test-output/screenshots/%s.png";
  public static String browserType;
  public static final int VERY_LONG_TIMEOUT = 100;
  public static final int LONG_TIMEOUT = 20;
  public static final int DEFAULT_TIMEOUT = 10;
  public static final int SHORT_TIMEOUT = 5;

  private WebDriver driver;
  private static Browser browser;

  private Browser() {
    switch (browserType) {
      case "chrome": {
        System.setProperty("webdriver.chrome.driver",
            "./src/main/resources/task11/chromedriver.exe");
        driver = new ChromeDriver();
        break;
      }
      case "mozilla": {
        System.setProperty("webdriver.gecko.driver",
            "./src/main/resources/task11/geckodriver.exe");
        driver = new FirefoxDriver();
        break;
      }
      default:
        throw new IllegalArgumentException("");
    }
    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    driver.manage().timeouts().pageLoadTimeout(20000, TimeUnit.MILLISECONDS);
  }

  public static Browser getInstance() {
    if (browser == null) {
      return browser = new Browser();
    } else {
      return browser;
    }
  }

  public void closeBrowser() {
    driver.quit();
    browser = null;
    Log.logCloseBrowser();
  }

  public Set<String> getWindowHandles() {
    Log.logGetWindowHandles();
    return driver.getWindowHandles();
  }

  public void switchToTab(String nameOfTab) {
    Log.logSwitchToTab(nameOfTab);
    driver.switchTo().window(nameOfTab);
  }


  public void getUrl(String url) {
    Log.logGetUrl(url);
    driver.get(url);
    driver.manage().window().maximize();
  }

  public void clickElement(By by) {
    Log.logClick(by);
    driver.findElement(by).click();
  }

  public void doubleClickElement(By by) {
    Log.logDoubleClick(by);
    Actions action = new Actions(driver);
    action.doubleClick(driver.findElement(by)).build().perform();
  }

  public void contextClickElement(By by) {
    Log.logContextClick(by);
    Actions action = new Actions(driver);
    action.contextClick(driver.findElement(by)).build().perform();
  }

  public void typeTo(By by, String value) {
    Log.logTypeTo(by, value);
    driver.findElement(by).sendKeys(value);
  }

  public void selectInElement(By by, String value) {
    Log.logSelect(by, value);
    Select select = new Select(driver.findElement(by));
    select.selectByVisibleText(value);
  }

  public boolean isDisplayed(By by) {
    Log.logIsDisplayed(by);
    return !driver.findElements(by).isEmpty();
  }

  public String getTextFrom(By by) {
    String text = driver.findElement(by).getText();
    Log.logGetTextFrom(by, text);
    return text;
  }

  public void waitForVisibility(By by, int seconds) {
    Log.logWaitForVisibility(by, seconds);
    new WebDriverWait(driver, seconds)
        .until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public void waitForPresence(By by, int seconds) {
    Log.logWaitForPresence(by, seconds);
    new WebDriverWait(driver, seconds)
        .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public void waitForStalenessOfElement(By by, int seconds) {
    Log.logWaitForStaleness(by, seconds);
    new WebDriverWait(driver, seconds)
        .until(ExpectedConditions.stalenessOf(driver.findElement(by)));
  }

  public void waitForTextInElementLocated(By by, int seconds, String text) {
    Log.logWaitForTextInElement(by, seconds, text);
    new WebDriverWait(driver, seconds)
        .until(ExpectedConditions.textToBePresentInElementLocated(by, text));
  }

  public void waitForElementToBeClickable(By by, int seconds) {
    Log.logWaitForElementToBeClickable(by, seconds);
    new WebDriverWait(driver, seconds)
        .until(ExpectedConditions.elementToBeClickable(by));
  }

  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  public void pressEscOnThePage() {
    Log.logPressEscOnThePage();
    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
  }

  public void pressCtrlAOnThePage() {
    Log.logPressCtrlAOnThePage();
    driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL, "a");
  }

  public void pressDelOnThePage() {
    Log.logPressDelOnThePage();
    driver.findElement(By.tagName("body")).sendKeys(Keys.DELETE);
  }

  public void takeScreenshotOnThePage(String fileName) {
    File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File destinationFile = new File(
        (format(PATTERN_PATH_TO_SCREENSHOT_FILE, fileName)));
    try {
      FileUtils.copyFile(screenFile, destinationFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Log.logTakeScreenshotOnThePage(destinationFile.getAbsolutePath());
  }
}
