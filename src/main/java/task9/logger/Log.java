package task9.logger;

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
    logger.debug(String.format("Click element located: [%s]", by));
  }

  public static void logTestFail(Throwable throwable) {
    logger.error(throwable.getMessage());
  }
}
