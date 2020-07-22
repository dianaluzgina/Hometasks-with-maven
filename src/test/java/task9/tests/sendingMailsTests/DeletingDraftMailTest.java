package task9.tests.sendingMailsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

import java.util.List;

public class DeletingDraftMailTest {
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
    private WriteNewEmailPage newMail;

    @BeforeMethod
    public void setUp() {
        Log.logInfo("Test started");
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRu(user);
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
