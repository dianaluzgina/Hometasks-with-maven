package task11.logger;

import static java.lang.String.format;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Log {

  private static Logger logger = Logger.getLogger("MyLogger");

  public static void logInfo(String message) {
    logger.info(message);
  }

  public static void logDebug(String message) {
    logger.debug(message);
  }

  public static void logClick(By by) {
    logger.debug(format("Click element located: [%s]", by));
  }

  public static void logGetUrl(String url) {
    logger.debug(format("Open page by URL: [%s], maximize page", url));
  }

  public static void logCloseBrowser() {
    logger.debug("Close browser");
  }

  public static void logSwitchToFrame(int index) {
    logger.debug(format("Switch to frame with index [%s]", index));
  }

  public static void logGetWindowHandles() {
    logger.debug("Find all open tabs in browser");
  }

  public static void logSwitchToTab(String nameOfTab) {
    logger.debug(format("Switch to tab with name [%s]", nameOfTab));
  }

  public static void logDoubleClick(By by) {
    logger.debug(format("DoubleClick element located: [%s]", by));
  }

  public static void logContextClick(By by) {
    logger.debug(format("ContextClick element located: [%s]", by));
  }

  public static void logTypeTo(By by, String value) {
    logger.debug(format("Type [%s] to element located: [%s]", value, by));
  }

  public static void logSelect(By by, String value) {
    logger.debug(format("Select [%s] in element located: [%s]", value, by));
  }

  public static void logIsDisplayed(By by) {
    logger.debug(format("Check that element located [%s] is displayed", by));
  }

  public static void logGetTextFrom(By by, String text) {
    logger.debug(format("Found text: [%s] on element located [%s]", text, by));
  }

  public static void logWaitForVisibility(By by, int seconds) {
    logger.debug(format("Wait for visibility of element located [%s] for [%s] seconds",
        by, seconds));
  }

  public static void logWaitForPresence(By by, int seconds) {
    logger.debug(format("Wait for presence of element located [%s] for [%s] seconds",
        by, seconds));
  }

  public static void logWaitForStaleness(By by, int seconds) {
    logger.debug(format("Wait for staleness of element located [%s] for [%s] seconds",
        by, seconds));
  }

  public static void logWaitForTextInElement(By by, int seconds, String text) {
    logger.debug(
        format("Wait for text [%s] in element located [%s] for [%s] seconds", text, by, seconds));
  }

  public static void logWaitForElementToBeClickable(By by, int seconds) {
    logger.debug(format("Wait for element located [%s] to be clickable for [%s] seconds",
        by, seconds));
  }

  public static void logTakeScreenshotOnThePage(String filePath) {
    logger.debug(format("Take screenshot on the page to the file [%s]", filePath));
  }

  public static void logPressEscOnThePage() {
    logger.debug("Press Esc on the page");
  }

  public static void logPressCtrlAOnThePage() {
    logger.debug("Press Ctrl+A on the page");
  }

  public static void logPressDelOnThePage() {
    logger.debug("Press Del on the page");
  }

  public static void logTestFail(Throwable throwable) {
    logger.error(throwable.getMessage());
  }
}