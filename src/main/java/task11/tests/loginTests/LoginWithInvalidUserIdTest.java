package task11.tests.loginTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task11.TestListener;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.MailRuLoginPage;
import task11.tests.BaseTest;

@Listeners({TestListener.class})
public class LoginWithInvalidUserIdTest extends BaseTest {

  private User testUser = UserFactory.getUserWithInvalidCredentials();

  @Test
  public void mailRuLoginWithInvalidUserId() {
    Log.logInfo("Test started");
    MailRuLoginPage page = new MailRuLoginPage();
    page.load()
        .typeUserName(testUser.getName())
        .selectDomain(testUser.getDomain())
        .clickPasswordButton();
    SoftAssert anAssert = new SoftAssert();
    anAssert.assertTrue(page.isErrorMessageDisplayed(), "Error message isn't displayed");
    anAssert.assertEquals(page.getErrorMessageText(), "Неверное имя ящика",
        "Error message on the page differs from message 'Неверное имя ящика'");
    anAssert.assertAll();
  }
}
