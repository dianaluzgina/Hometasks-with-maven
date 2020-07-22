package task9.tests.sendingMailsTests;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task9.entities.Browser;
import task9.bo.Mail;
import task9.bo.MailFactory;
import task9.bo.User;
import task9.bo.UserFactory;
import task9.logger.Log;
import task9.screens.WriteNewEmailPage;
import task9.services.LoginService;

public class SendingMailToMyselfTest {
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
    private WriteNewEmailPage newMail;

    @BeforeMethod
    public void setUp() {
        Log.logInfo("Test started");
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRu(user);
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
