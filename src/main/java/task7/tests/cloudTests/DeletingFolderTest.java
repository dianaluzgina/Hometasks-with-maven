package task7.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task7.Entities.Browser;
import task7.bo.Folder;
import task7.bo.FolderFactory;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.MailRuCloudMainPage;
import task7.services.LoginService;

import java.util.List;

public class DeletingFolderTest {
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
        cloudPage.clickSelectAllButton()
                .clickDeleteButton()
                .clickApproveDeleteButton()
                .clickCloseInformingMessageButton()
                .clickCloudButton();
        List<WebElement> elementsInTheCloud = cloudPage.getElementsInTheCloudOrFolder();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(nameOfTheFirstFolderInTheLeftPanel, uniqueName, "Name of the folder in the cloud differs from which we've created");
        anAssert.assertEquals(elementsInTheCloud.size(), 0, "There are some elements in the cloud");
        anAssert.assertAll();
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
