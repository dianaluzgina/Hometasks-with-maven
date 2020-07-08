package task7.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.*;
import task7.Entities.Browser;
import task7.bo.*;
import task7.screens.MailRuCloudMainPage;
import task7.services.LoginService;

public class CreatingFolderTest {
    private Folder folder = FolderFactory.getFolderWithUniqueName();
    private MailRuCloudMainPage cloudPage = new MailRuCloudMainPage();

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
        Browser.getInstance().closeBrowser();
    }
}
