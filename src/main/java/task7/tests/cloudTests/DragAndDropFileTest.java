package task7.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task7.bo.Folder;
import task7.bo.FolderFactory;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.MailRuCloudMainPage;
import task7.services.LoginService;

import java.io.File;
import java.util.List;

public class DragAndDropFileTest {
    private static final String PATH_TO_THE_FILE_FOR_DOWNLOADING = "src/main/resources/task7/fileToLoad.txt";
    private static final String FILE_NAME = "fileToLoad";

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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cloudPage.dragAndDropSecondElementByCoordinates()
                    .clickMoveButton()
                    .doubleClickToTheFirstElement();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elementsInTheFolder = cloudPage.getElementsInTheCloudOrFolder();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(elementsInTheFolder.size(), 1, "Quantity of elements in the folder differs from we've dropped");
        anAssert.assertEquals(elementsInTheFolder.get(0).getText(), FILE_NAME, "Name of the file differs from which we've dropped");
        anAssert.assertAll();

    }


//    @AfterMethod
//    public void cleanAfterTest() {
//        cloudPage.clickCloudButton()
//                .clickSelectAllButton()
//                .cleanCloudAfterTest();
//    }

//    @AfterClass(alwaysRun = true)
//    public void tearDown() {
//        Browser.getInstance().closeBrowser();
//    }
}
