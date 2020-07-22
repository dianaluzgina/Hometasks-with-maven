package task9.tests.loginTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task9.entities.Browser;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.MailRuLoginPage;

public class LoginWithInvalidUserIdTest {
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
        anAssert.assertEquals(page.getErrorMessageText(), "Неверное имя ящика");
        anAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
