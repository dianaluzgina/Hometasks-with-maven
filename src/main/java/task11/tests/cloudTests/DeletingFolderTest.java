package task11.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task11.TestListener;
import task11.bo.Folder;
import task11.bo.FolderFactory;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;
import task11.services.CleanCloudService;
import task11.services.LoginService;

import java.util.List;
import task11.tests.BaseTest;

@Listeners({TestListener.class})
public class DeletingFolderTest extends BaseTest {

  private Folder folder = FolderFactory.getFolderWithUniqueName();
  private MailRuCloudMainPage cloudPage;

  @BeforeMethod
  public void setUp() {
    Log.logInfo("Test started");
    User user = UserFactory.getUserWithValidCredentials();
    LoginService.loginToMailRuCloud(user);
    cloudPage = new MailRuCloudMainPage();
    cloudPage.switchToTheTabByIndex(2);
    CleanCloudService.cleanCloudBeforeTest();
  }

  @Test
  public void deletingFolder() {
    String uniqueName = folder.getName();
    cloudPage.clickCreateButton()
        .clickCreateFolderButton()
        .typeNameOfTheFolder(uniqueName)
        .clickCreateFolderButtonInTheFrame()
        .clickCloudButton();
    String nameOfTheFirstFolderInTheLeftPanel = cloudPage.getNameOfTheFirstFolderInTheLeftPanel();
    cloudPage.clickSelectAllButton()
        .clickDeleteButton()
        .clickApproveDeleteButton()
        .clickCloseInformingMessageButton()
        .clickCloudButton();
    List<String> textsOfElementsInTheCloud = cloudPage.getTextFromElementsInTheCloudOrFolder();
    SoftAssert anAssert = new SoftAssert();
    anAssert.assertEquals(nameOfTheFirstFolderInTheLeftPanel, uniqueName,
        "Name of the folder in the cloud differs from which we've created");
    anAssert
        .assertEquals(textsOfElementsInTheCloud.size(), 0, "There are some elements in the cloud");
    anAssert.assertAll();
  }

  @AfterMethod
  public void cleanAfterTest() {
    cloudPage.clickCloudButton();
    CleanCloudService.cleanCloudAfterTest();
  }
}
