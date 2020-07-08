package task7.tests.loginTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task7.Entities.Browser;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.MailRuLoginPage;

public class LoginWithInvalidPasswordTest {
    private User testUser = UserFactory.getUserWithInvalidPassword();

    @Test
    public void mailRuLoginWithInvalidPassword() {
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
        Browser.getInstance().closeBrowser();
    }
}
