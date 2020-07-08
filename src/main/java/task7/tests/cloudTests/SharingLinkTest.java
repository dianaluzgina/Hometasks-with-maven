package task7.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.*;
import task7.Entities.Browser;
import task7.bo.*;
import task7.screens.MailRuCloudMainPage;
import task7.screens.WriteNewEmailPage;
import task7.services.LoginService;

public class SharingLinkTest {
    private Folder folder = FolderFactory.getFolderWithUniqueName();
    private MailRuCloudMainPage cloudPage = new MailRuCloudMainPage();
    private WriteNewEmailPage newMail = new WriteNewEmailPage();
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();

    @BeforeClass
    public void setUp() {
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRuCloud(user);
    }

    @BeforeMethod
    public void cleanBeforeTest() {
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
        Browser.getInstance().closeBrowser();
    }
}
