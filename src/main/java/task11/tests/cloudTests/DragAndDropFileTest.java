package task11.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task11.TestListener;
import task11.bo.Folder;
import task11.bo.FolderFactory;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.entities.Browser;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;
import task11.services.LoginService;

import java.io.File;
import java.util.List;

@Listeners({TestListener.class})
public class DragAndDropFileTest {
    private static final String PATH_TO_THE_FILE_FOR_DOWNLOADING = "src/main/resources/task11/fileToLoad.txt";
    private static final String FILE_NAME = "fileToLoad";

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
    public void DragAndDropFile() {
        File file = new File(PATH_TO_THE_FILE_FOR_DOWNLOADING);
        String uniqueFolderName = folder.getName();
        cloudPage.clickCreateButton()
                .clickCreateFolderButton()
                .typeNameOfTheFolder(uniqueFolderName)
                .clickCreateFolderButtonInTheFrame();
        if (cloudPage.isMessageThatFolderIsEmptyVisible()) {
            cloudPage.clickCloudButton()
                    .clickLoadButton()
                    .typePathToTheFileForDownloading(file.getAbsolutePath());
        }
        if (cloudPage.isDownloadingOfFileComplete()) {
            cloudPage.contextClickOnTheSecondElement()
                .clickMoveElementButtonInContextMenu()
                .clickActivateFolder(uniqueFolderName)
                .clickMoveButton()
                .doubleClickToTheFirstElement();
        }
        List<String> textsOfElementsInTheFolder = cloudPage.getTextFromElementsInTheCloudOrFolder();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(textsOfElementsInTheFolder.size(), 1, "Quantity of elements in the folder differs from we've dropped");
        anAssert.assertEquals(textsOfElementsInTheFolder.get(0), FILE_NAME, "Name of the file differs from which we've dropped");
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
