package task11.tests.loginTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task11.TestListener;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.EmailMainPage;
import task11.services.LoginService;

@Listeners({TestListener.class})
public class LoginWithValidCredentialsTest {
    private User testUser = UserFactory.getUserWithValidCredentials();

    @Test
    public void mailRuLoginWithValidCredentials() {
        Log.logInfo("Test started");
        LoginService.loginToMailRu(testUser);
        Assert.assertEquals(new EmailMainPage().getUserID(), testUser.getName() + testUser.getDomain(),
                "User id wasn't found!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
