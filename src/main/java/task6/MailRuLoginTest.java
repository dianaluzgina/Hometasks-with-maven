package task6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task6.bo.Mail;
import task6.bo.MailFactory;
import task6.bo.User;
import task6.bo.UserFactory;
import task6.screens.EmailMainPage;
import task6.screens.MailRuLoginPage;


public class MailRuLoginTest {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/task6/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void mailRuTestLoginWithInvalidCredentials() {
        User user= UserFactory.getUserWithInvalidCredentials();
        MailRuLoginPage loginPageObject = new MailRuLoginPage(driver);
        loginPageObject.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(loginPageObject.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(loginPageObject.getErrorMessageText(), "Неверное имя ящика");
        anAssert.assertAll();
    }

    @Test
    public void mailRuTestLoginWithInvalidPassword() {
        User user= UserFactory.getUserWithInvalidPassword();
        MailRuLoginPage loginPageObject = new MailRuLoginPage(driver);
        loginPageObject.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton()
                .typePassword(user.getPassword())
                .clickSubmitButton();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(loginPageObject.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(loginPageObject.getErrorMessageText(), "Неверное имя или пароль");
        anAssert.assertAll();
    }

    @Test
    public void mailRuTestLoginWithValidCredentials() {
        User user= UserFactory.getUserWithValidCredentials();
        MailRuLoginPage loginPageObject = new MailRuLoginPage(driver);
        EmailMainPage emailMainPage= loginPageObject.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton()
                .typePassword(user.getPassword())
                .clickSubmitButton();
                Assert.assertTrue(emailMainPage.isInboxFolderDisplayed(), "Inbox folder isn't displayed");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
