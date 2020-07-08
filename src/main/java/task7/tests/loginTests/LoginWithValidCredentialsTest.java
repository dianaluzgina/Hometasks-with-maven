package task7.tests.loginTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import task7.Entities.Browser;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.EmailMainPage;
import task7.services.LoginService;

public class LoginWithValidCredentialsTest {
    private User testUser = UserFactory.getUserWithValidCredentials();

    @Test
    public void mailRuLoginWithValidCredentials() {
        LoginService.LoginToMailRu(testUser);
        Assert.assertEquals(new EmailMainPage().getUserID(), testUser.getName()+testUser.getDomain(), "User id wasn't found!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Browser.getInstance().closeBrowser();
    }
}
