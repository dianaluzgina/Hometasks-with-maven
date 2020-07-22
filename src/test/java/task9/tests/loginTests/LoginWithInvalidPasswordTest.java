package task9.tests.loginTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task9.entities.Browser;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.MailRuLoginPage;

public class LoginWithInvalidPasswordTest {
    private User testUser = UserFactory.getUserWithInvalidPassword();

    @Test
    public void mailRuLoginWithInvalidPassword() {
        Log.logInfo("Test started");
        MailRuLoginPage page = new MailRuLoginPage();
        page.load()
                .typeUserName(testUser.getName())
                .selectDomain(testUser.getDomain())
                .clickPasswordButton()
                .typePassword(testUser.getPassword())
                .clickSubmitButton();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(page.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(page.getErrorMessageText(), "Неверное имя или пароль");
        anAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
