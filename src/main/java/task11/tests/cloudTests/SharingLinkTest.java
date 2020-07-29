package task11.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.*;
import task11.TestListener;
import task11.bo.Folder;
import task11.bo.FolderFactory;
import task11.bo.Mail;
import task11.bo.MailFactory;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.entities.Browser;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;
import task11.screens.WriteNewEmailPage;
import task11.services.CleanCloudService;
import task11.services.LoginService;
import task11.tests.BaseTest;

@Listeners({TestListener.class})
public class SharingLinkTest extends BaseTest {

  private Folder folder = FolderFactory.getFolderWithUniqueName();
  private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
  private MailRuCloudMainPage cloudPage;
  private WriteNewEmailPage newMail;

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
  public void sharingLinkToFolder() {
    String uniqueName = folder.getName();
    cloudPage.clickCreateButton()
        .clickCreateFolderButton()
        .typeNameOfTheFolder(uniqueName)
        .clickCreateFolderButtonInTheFrame();
    if (cloudPage.isMessageThatFolderIsEmptyVisible()) {
      cloudPage.clickCloudButton()
          .contextClickOnTheFirstElement()
          .clickShareLink()
          .clickSendLinkBYMail()
          .switchToTheTabByIndex(3);
    }
    newMail = new WriteNewEmailPage();
    newMail.typeAddressInput(mail.getRecipient());
    String linkToPublicFolder = newMail.getTextOfTheBodyWithLinkToPublicFolder();
    newMail.clickSendMailButton()
        .clickClosePanelMailIsSent();
    cloudPage.loadUrl(linkToPublicFolder);
    String nameOfThePublicFolder = cloudPage.getNameOfThePublicFolder();
    Assert.assertEquals(nameOfThePublicFolder, uniqueName,
        "Name of the public folder differs from which we've created");
  }

  @AfterMethod
  public void cleanAfterTest() {
    cloudPage.switchToTheTabByIndex(2)
        .pressEscOnThePage()
        .pressCtrlAOnThePage();
    CleanCloudService.cleanCloudAfterTest();
        cloudPage.switchToTheTabByIndex(1);
    newMail.clickToMyselfFolder()
        .cleanFolder()
        .clickSentFolder()
        .cleanFolder();
  }
}
