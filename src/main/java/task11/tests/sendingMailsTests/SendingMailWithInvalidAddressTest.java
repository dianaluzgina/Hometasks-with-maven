package task11.tests.sendingMailsTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task11.TestListener;
import task11.bo.Mail;
import task11.bo.MailFactory;
import task11.entities.Browser;
import task11.bo.User;
import task11.bo.UserFactory;
import task11.logger.Log;
import task11.screens.WriteNewEmailPage;
import task11.services.LoginService;
import task11.tests.BaseTest;

@Listeners({TestListener.class})
public class SendingMailWithInvalidAddressTest extends BaseTest {

  private Mail mail = MailFactory.getMailWithInvalidAddress();
  private WriteNewEmailPage newMail;

  @BeforeClass
  public void setUp() {
    Log.logInfo("Test started");
    User user = UserFactory.getUserWithValidCredentials();
    LoginService.loginToMailRu(user);
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
    anAssert.assertEquals(newMail.getErrorMessageText(), "Присутствуют некорректные адреса",
        "Text of error message differs from we've expected");
    anAssert.assertTrue(newMail.isErrorMessageToChangeTheMailDisplayed(),
        "Second error message isn't displayed");
    anAssert.assertEquals(newMail.getErrorMessageToChangeTheMailText(),
        "Исправьте и попробуйте отправить заново",
        "Text of error message differs from we've expected");
    anAssert.assertAll();
  }
}
