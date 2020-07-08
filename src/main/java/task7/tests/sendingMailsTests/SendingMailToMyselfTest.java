package task7.tests.sendingMailsTests;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task7.Entities.Browser;
import task7.bo.Mail;
import task7.bo.MailFactory;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.WriteNewEmailPage;
import task7.services.LoginService;

public class SendingMailToMyselfTest {
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
    private WriteNewEmailPage newMail = new WriteNewEmailPage();

    @BeforeClass
    public void setUp() {
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRu(user);
    }

    @BeforeMethod
    public void cleanSentFolderAndToMyselfFolderBeforeTest() {
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
        Browser.getInstance().closeBrowser();
    }
}
