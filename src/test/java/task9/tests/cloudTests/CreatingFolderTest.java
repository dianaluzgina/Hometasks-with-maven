package task9.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.*;
import task9.entities.Browser;
import task9.bo.Folder;
import task9.bo.FolderFactory;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.MailRuCloudMainPage;
import task9.services.LoginService;

public class CreatingFolderTest {
    private Folder folder = FolderFactory.getFolderWithUniqueName();
    private MailRuCloudMainPage cloudPage;

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
