package task11.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task11.TestListener;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;
import task11.services.LoginService;

import java.io.File;
import java.util.List;

@Listeners({TestListener.class})
public class UploadingFileTest {
    private static final String PATH_TO_THE_FILE_FOR_DOWNLOADING = "src/main/resources/task11/fileToLoad.txt";
    private static final String FILE_NAME = "fileToLoad";

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
    public void uploadingFile() {
        File file = new File(PATH_TO_THE_FILE_FOR_DOWNLOADING);
        cloudPage.clickLoadButton()
                .typePathToTheFileForDownloading(file.getAbsolutePath());
        if (cloudPage.isDownloadingOfFileComplete()){
            cloudPage.clickCloudButton();
        }
        List<String> textsOfElementsInTheCloud = cloudPage.getTextFromElementsInTheCloudOrFolder();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(textsOfElementsInTheCloud.size(), 1, "Quantity of elements in the cloud differ from we've downloaded");
        anAssert.assertEquals(textsOfElementsInTheCloud.get(0), FILE_NAME, "Name of the file differs from which we've downloaded");
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
