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

public class SendingMailWithoutSubjectAndBodyTest {
    private Mail mail = MailFactory.getMailWithoutSubjectAndBody();
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
        anAssert.assertEquals(subjectOfFirstMailInToMyselfFolder, "<Без темы>", "Subject of mail in ToMyself folder differs from which we've sent");
        anAssert.assertEquals(subjectOfFirstMailInSentFolder, "<Без темы>", "Subject of mail in Sent folder differs from which we've sent");
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

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Browser.getInstance().closeBrowser();
    }
}
