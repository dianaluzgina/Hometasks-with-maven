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

@Listeners({TestListener.class})
public class SendingMailToMyselfTest {
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
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
                .cleanFolder();
    }

    @Test
    public void sendingMailToMyself() {
        String uniqueSubject = mail.getSubject();
        newMail.clickWriteTheMailButton();
        newMail.typeAddressInput(mail.getRecipient())
                .typeSubjectInput(uniqueSubject)
                .typeBodyInput(mail.getBodyLetter())
                .clickSendMailButton()
                .clickClosePanelMailIsSent()
                .clickToMyselfFolder();
        String subjectOfFirstMailInToMyselfFolder = newMail.getTextOfSubjectOfFirstMail();
        newMail.clickSentFolder();
        String subjectOfFirstMailInSentFolder = newMail.getTextOfSubjectOfFirstMail();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(subjectOfFirstMailInToMyselfFolder, uniqueSubject, "Subject of mail in ToMyself folder differs from which we've sent");
        anAssert.assertEquals(subjectOfFirstMailInSentFolder, uniqueSubject, "Subject of mail in Sent folder differs from which we've sent");
        anAssert.assertAll();
    }

    @AfterMethod
    public void cleanSentFolderAndToMyselfFolderAfterTest() {
        newMail.clickSentFolder()
                .cleanFolder()
                .clickToMyselfFolder()
                .cleanFolder();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
