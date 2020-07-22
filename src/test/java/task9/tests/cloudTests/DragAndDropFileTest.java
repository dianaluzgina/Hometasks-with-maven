package task9.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task9.bo.Folder;
import task9.bo.FolderFactory;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.entities.Browser;
import task9.logger.Log;
import task9.screens.MailRuCloudMainPage;
import task9.services.LoginService;

import java.io.File;
import java.util.List;

public class DragAndDropFileTest {
    private static final String PATH_TO_THE_FILE_FOR_DOWNLOADING = "src/main/resources/task9/fileToLoad.txt";
    private static final String FILE_NAME = "fileToLoad";

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
    public void DragAndDropFile() {
        File file = new File(PATH_TO_THE_FILE_FOR_DOWNLOADING);
        String uniqueName = folder.getName();
        cloudPage.clickCreateButton()
                .clickCreateFolderButton()
                .typeNameOfTheFolder(uniqueName)
                .clickCreateFolderButtonInTheFrame();
        if (cloudPage.isMessageThatFolderIsEmptyVisible()) {
            cloudPage.clickCloudButton()
                    .clickLoadButton()
                    .typePathToTheFileForDownloading(file.getAbsolutePath());
        }
        if (cloudPage.isDownloadingOfFileComplete()) {

            cloudPage.dragAndDropSecondElementByCoordinates()
                    .clickMoveButton()
                    .doubleClickToTheFirstElement();
        }

        List<WebElement> elementsInTheFolder = cloudPage.getElementsInTheCloudOrFolder();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(elementsInTheFolder.size(), 1, "Quantity of elements in the folder differs from we've dropped");
        anAssert.assertEquals(elementsInTheFolder.get(0).getText(), FILE_NAME, "Name of the file differs from which we've dropped");
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
