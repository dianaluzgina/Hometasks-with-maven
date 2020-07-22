package task9.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task9.entities.Browser;
import task9.bo.Folder;
import task9.bo.FolderFactory;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.MailRuCloudMainPage;
import task9.services.LoginService;

import java.util.List;

public class DeletingFolderTest {
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
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
