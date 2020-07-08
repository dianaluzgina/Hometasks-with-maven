package task7.tests.sendingMailsTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task7.Entities.Browser;
import task7.bo.Mail;
import task7.bo.MailFactory;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.WriteNewEmailPage;
import task7.services.LoginService;

public class SendingMailWithInvalidAddressTest {
private Mail mail = MailFactory.getMailWithInvalidAddress();
private WriteNewEmailPage newMail = new WriteNewEmailPage();

    @BeforeClass
    public void setUp() {
        User user= UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRu(user);
    }

    @Test
    public void sendingMailWithInvalidAddress() {
        newMail.clickWriteTheMailButton();
        newMail.typeAddressInput(mail.getRecipient())
                .typeSubjectInput(mail.getSubject())
                .typeBodyInput(mail.getBodyLetter())
                .clickSendMailButton();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(newMail.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(newMail.getErrorMessageText(), "Присутствуют некорректные адреса");
        anAssert.assertTrue(newMail.isErrorMessageToChangeTheMailDisplayed(), "Second error message isn't displayed");
        anAssert.assertEquals(newMail.getErrorMessageToChangeTheMailText(), "Исправьте и попробуйте отправить заново");
        anAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Browser.getInstance().closeBrowser();
    }
}
