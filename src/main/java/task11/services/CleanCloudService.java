package task11.services;

import java.util.List;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;

public class CleanCloudService {

  public static void cleanCloudBeforeTest() {
    Log.logInfo("Cleaning cloud before test");
    MailRuCloudMainPage page = new MailRuCloudMainPage();
    page.clickSelectAllButton();
    List<String> textsOfElementsInTheCloud = page.getTextFromElementsInTheCloudOrFolder();
    if (textsOfElementsInTheCloud.size() > 0) {
      page.clickDeleteButton()
          .clickApproveDeleteButton()
          .clickCloseInformingMessageButton();
    }
  }

  public static void cleanCloudAfterTest() {
    Log.logInfo("Cleaning cloud after test");
    MailRuCloudMainPage page = new MailRuCloudMainPage();
    page.clickSelectAllButton();
    List<String> textsOfElementsInTheCloud = page.getTextFromElementsInTheCloudOrFolder();
    if (textsOfElementsInTheCloud.size() > 0) {
      page.clickDeleteButton()
          .clickApproveDeleteButton();
    }
  }

}
