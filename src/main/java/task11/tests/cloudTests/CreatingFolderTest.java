package task11.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.*;
import task11.TestListener;
import task11.bo.Folder;
import task11.bo.FolderFactory;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;
import task11.services.LoginService;

@Listeners({TestListener.class})
public class CreatingFolderTest {
    private Folder folder = FolderFactory.getFolderWithUniqueName();
    private MailRuCloudMainPage cloudPage;

    @BeforeMethod
    public void setUp() {
        Log.logInfo("Test started");
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.loginToMailRuCloud(user);
        cloudPage = new MailRuCloudMainPage();
        cloudPage.switchToTheTabByIndex(2)
                .clickSelectAllButton()
                .cleanCloudBeforeTest();
    }

    @Test
    public void creatingFolder() {
        String uniqueName = folder.getName();
        cloudPage.clickCreateButton()
                .clickCreateFolderButton()
                .typeNameOfTheFolder(uniqueName)
                .clickCreateFolderButtonInTheFrame()
                .clickCloudButton();
        String nameOfTheFirstFolderInTheLeftPanel = cloudPage.getNameOfTheFirstFolderInTheLeftPanel();
        Assert.assertEquals(nameOfTheFirstFolderInTheLeftPanel, uniqueName, "Name of the folder in the cloud differs from which we've created");
    }

    @AfterMethod
    public void cleanAfterTest() {
        cloudPage.clickCloudButton()
                .clickSelectAllButton()
                .cleanCloudAfterTest();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
