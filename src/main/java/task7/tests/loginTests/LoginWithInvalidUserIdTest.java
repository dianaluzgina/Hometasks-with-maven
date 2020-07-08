package task7.tests.loginTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task7.Entities.Browser;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.EmailMainPage;
import task7.screens.MailRuLoginPage;
import task7.services.LoginService;

public class LoginWithInvalidUserIdTest {
    private User testUser = UserFactory.getUserWithInvalidCredentials();

    @Test
    public void mailRuLoginWithInvalidUserId() {
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
        Browser.getInstance().closeBrowser();
    }
}
