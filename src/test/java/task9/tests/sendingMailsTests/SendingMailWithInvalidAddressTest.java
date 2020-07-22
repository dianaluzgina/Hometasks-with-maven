package task9.tests.sendingMailsTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task9.entities.Browser;
import task9.bo.Mail;
import task9.bo.MailFactory;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.WriteNewEmailPage;
import task9.services.LoginService;

public class SendingMailWithInvalidAddressTest {
private Mail mail = MailFactory.getMailWithInvalidAddress();
private WriteNewEmailPage newMail;

    @BeforeClass
    public void setUp() {
        Log.logInfo("Test started");
        User user= UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRu(user);
    }

    @Test
    public void sendingMailWithInvalidAddress() {
        newMail = new WriteNewEmailPage();
        newMail.clickWriteTheMailButton();
        newMail.typeAddressInput(mail.getRecipient())
                .typeSubjectInput(mail.getSubject())
                .typeBodyInput(mail.getBodyLetter())
                .clickSendMailButton();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(newMail.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(newMail.getErrorMessageText(), "Присутствуют некорректные адреса", "Text of error message differs from we've expected");
        anAssert.assertTrue(newMail.isErrorMessageToChangeTheMailDisplayed(), "Second error message isn't displayed");
        anAssert.assertEquals(newMail.getErrorMessageToChangeTheMailText(), "Исправьте и попробуйте отправить заново", "Text of error message differs from we've expected");
        anAssert.assertAll();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
