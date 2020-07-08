package task7.tests.sendingMailsTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import task7.Entities.Browser;
import task7.bo.Mail;
import task7.bo.MailFactory;
import task7.bo.User;
import task7.bo.UserFactory;
import task7.screens.WriteNewEmailPage;
import task7.services.LoginService;

import java.util.List;

public class DeletingDraftMailTest {
    private Mail mail = MailFactory.getMailWhereRecipientEqualsSender();
    private WriteNewEmailPage newMail = new WriteNewEmailPage();

    @BeforeClass
    public void setUp() {
        User user = UserFactory.getUserWithValidCredentials();
        LoginService.LoginToMailRu(user);
    }

    @BeforeMethod
    public void cleanDraftsFolderAndTrashFolderBeforeTest() {
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
        Browser.getInstance().closeBrowser();
    }
}
