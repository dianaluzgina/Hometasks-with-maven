package task9.tests.cloudTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task9.entities.Browser;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.screens.MailRuCloudMainPage;
import task9.services.LoginService;

import java.io.File;
import java.util.List;

public class UploadingFileTest {
    private static final String PATH_TO_THE_FILE_FOR_DOWNLOADING = "src/main/resources/task7/fileToLoad.txt";
    private static final String FILE_NAME = "fileToLoad";

    private MailRuCloudMainPage cloudPage;

    @BeforeClass
    public void setUp() {
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRuCloud(user);
    }

    @BeforeMethod
    public void cleanBeforeTest() {
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRuCloud(user);
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
        List<WebElement> elementsInTheCloud = cloudPage.getElementsInTheCloudOrFolder();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(elementsInTheCloud.size(), 1, "Quantity of elements in the cloud differ from we've downloaded");
        anAssert.assertEquals(elementsInTheCloud.get(0).getText(), FILE_NAME, "Name of the file differs from which we've downloaded");
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
