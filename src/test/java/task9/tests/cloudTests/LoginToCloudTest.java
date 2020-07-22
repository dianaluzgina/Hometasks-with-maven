package task9.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import task9.entities.Browser;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.MailRuCloudMainPage;
import task9.services.LoginService;

public class LoginToCloudTest {
    private User testUser = UserFactory.getUserWithValidCredentials();

    @Test
    public void mailRuLoginWithValidCredentials() {
        Log.logInfo("Test started");
        LoginService.LoginToMailRuCloud(testUser);
        Assert.assertEquals(new MailRuCloudMainPage().getUserID(), testUser.getName()+testUser.getDomain(), "User id wasn't found!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
