package task11.tests.cloudTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task11.TestListener;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.MailRuCloudMainPage;
import task11.services.LoginService;
import task11.tests.BaseTest;

@Listeners({TestListener.class})
public class LoginToCloudTest extends BaseTest {

  private User testUser = UserFactory.getUserWithValidCredentials();

  @Test
  public void loginToCloud() {
    Log.logInfo("Test started");
    LoginService.loginToMailRuCloud(testUser);
    Assert.assertEquals(new MailRuCloudMainPage().getUserID(),
        testUser.getName() + testUser.getDomain(), "User id wasn't found!");
  }
}
