package task9.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.*;
import task9.entities.Browser;
import task9.bo.*;
import task9.logger.Log;
import task9.screens.MailRuCloudMainPage;
import task9.screens.WriteNewEmailPage;
import task9.services.LoginService;

public class SharingLinkTest {
    private Folder folder = FolderFactory.getFolderWithUniqueName();
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
    private MailRuCloudMainPage cloudPage;
    private WriteNewEmailPage newMail;

    @BeforeMethod
        public void setUp() {
        Log.logInfo("Test started");
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRuCloud(user);
        cloudPage = new MailRuCloudMainPage();
        cloudPage.switchToTheTabByIndex(2)
                .clickSelectAllButton()
                .cleanCloudBeforeTest();
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
        Assert.assertEquals(nameOfThePublicFolder, uniqueName, "Name of the public folder differs from which we've created");
    }

    @AfterMethod
    public void cleanAfterTest() {
        cloudPage.switchToTheTabByIndex(2)
                .pressEscOnThePage()
                .pressCtrlAOnThePage()
                .cleanCloudAfterTest()
                .switchToTheTabByIndex(1);
        newMail.clickSentFolder()
                .cleanFolder()
                .clickToMyselfFolder()
                .clickSelectAllMailsButton()
                .pressDelOnThePage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
