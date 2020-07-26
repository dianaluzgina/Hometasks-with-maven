package task11.tests.sendingMailsTests;

import org.openqa.selenium.WebElement;
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

import java.util.List;

@Listeners({TestListener.class})
public class DeletingDraftMailTest {
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
    private WriteNewEmailPage newMail;

    @BeforeMethod
    public void setUp() {
        Log.logInfo("Test started");
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.loginToMailRu(user);
        newMail = new WriteNewEmailPage();
        newMail.clickDraftsFolder()
                .pressCtrlAOnThePage()
                .pressDelOnThePage()
                .clickTrashFolder()
                .clickClearTrashFolderButton()
                .clickApproveClearingTrashFolderButton();
    }

    @Test
    public void deletingDraftMail() {
        String uniqueSubject = mail.getSubject();
        newMail.clickWriteTheMailButton();
        newMail.typeAddressInput(mail.getRecipient())
                .typeSubjectInput(uniqueSubject)
                .typeBodyInput(mail.getBodyLetter())
                .clickSaveDraftMailButton()
                .clickCloseDraftMailButton()
                .clickDraftsFolder();
        String subjectOfFirstMailInDraftsFolder = newMail.getTextOfSubjectOfFirstMail();
        if (uniqueSubject.equals(subjectOfFirstMailInDraftsFolder)){
            newMail.selectCheckboxOfMailByIndex(1)
                    .clickDeleteTheMailButton();
        }
        newMail.clickTrashFolder();
        String subjectOfFirstMailInTrashFolder = newMail.getTextOfSubjectOfFirstMail();
        if (uniqueSubject.equals(subjectOfFirstMailInTrashFolder)){
            newMail.clickClearTrashFolderButton()
                    .clickApproveClearingTrashFolderButton();
        }
        newMail.clickTrashFolder();
        List<WebElement> subjectsOfMailsInTrashFolder = newMail.getSubjectsOfMails();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertEquals(subjectOfFirstMailInDraftsFolder, uniqueSubject, "Subject of mail in Draft folder differs from which we've created");
        anAssert.assertEquals(subjectOfFirstMailInTrashFolder, uniqueSubject, "Subject of mail in Trash folder differs from which we've deleted");
        anAssert.assertEquals(subjectsOfMailsInTrashFolder.size(), 0, "Male hasn't been deleted from Trash folder");
        anAssert.assertAll();
    }

    @AfterMethod
    public void cleanDraftsFolderAndTrashFolderAfterTest() {
        newMail.clickDraftsFolder()
                .cleanFolder()
                .clickTrashFolder()
                .cleanFolder();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.logInfo("Test finished");
        Browser.getInstance().closeBrowser();
    }
}
