package task7.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import task7.Entities.Browser;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.MailRuCloudMainPage;
import task7.services.LoginService;

public class LoginToCloudTest {
    private User testUser = UserFactory.getUserWithValidCredentials();

    @Test
    public void mailRuLoginWithValidCredentials() {
        LoginService.LoginToMailRuCloud(testUser);
        Assert.assertEquals(new MailRuCloudMainPage().getUserID(), testUser.getName()+testUser.getDomain(), "User id wasn't found!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Browser.getInstance().closeBrowser();
    }
}
