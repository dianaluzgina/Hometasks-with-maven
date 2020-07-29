package task11.tests.sendingMailsTests;

import org.testng.annotations.*;
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
public class SendingMailWithoutSubjectAndBodyTest extends BaseTest {

  private Mail mail = MailFactory.getMailWithoutSubjectAndBody();
  private WriteNewEmailPage newMail;

  @BeforeMethod
  public void setUp() {
    Log.logInfo("Test started");
    User user = UserFactory.getUserWithValidCredentials();
    LoginService.loginToMailRu(user);
    newMail = new WriteNewEmailPage();
    newMail.clickSentFolder()
        .cleanFolder()
        .clickToMyselfFolder()
        .pressCtrlAOnThePage()
        .pressDelOnThePage();
  }

  @Test
  public void sendingMailWithoutSubjectAndBody() {
    newMail.clickWriteTheMailButton();
    newMail.typeAddressInput(mail.getRecipient())
        .clickSendMailButton()
        .clickSendEmptyMailButton()
        .clickClosePanelMailIsSent()
        .clickToMyselfFolder();
    String subjectOfFirstMailInToMyselfFolder = newMail.getTextOfSubjectOfFirstMail();
    newMail.clickSentFolder();
    String subjectOfFirstMailInSentFolder = newMail.getTextOfSubjectOfFirstMail();
    SoftAssert anAssert = new SoftAssert();
    anAssert.assertEquals(subjectOfFirstMailInToMyselfFolder, "<Без темы>",
        "Subject of mail in ToMyself folder differs from which we've sent");
    anAssert.assertEquals(subjectOfFirstMailInSentFolder, "<Без темы>",
        "Subject of mail in Sent folder differs from which we've sent");
    anAssert.assertAll();
  }

  @AfterMethod
  public void cleanSentFolderAndToMyselfFolderAfterTest() {
    newMail.clickSentFolder()
        .pressCtrlAOnThePage()
        .pressDelOnThePage()
        .clickToMyselfFolder()
        .pressCtrlAOnThePage()
        .pressDelOnThePage();
  }
}
