package task6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task6.bo.Mail;
import task6.bo.MailFactory;
import task6.bo.User;
import task6.bo.UserFactory;
import task6.screens.EmailMainPage;
import task6.screens.MailRuLoginPage;

import java.util.List;

public class SendMailTest {
   private WebDriver driver;
   private EmailMainPage emailMainPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/task6/chromedriver.exe");
        driver = new ChromeDriver();
        User user= UserFactory.getUserWithValidCredentials();
        MailRuLoginPage loginPageObject = new MailRuLoginPage(driver);
        emailMainPage= loginPageObject.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton()
                .typePassword(user.getPassword())
                .clickSubmitButton();
    }

    @Test
    public void sendingLetterToMyself() {
        Mail mail= MailFactory.getMailWhereRecipientEqualsSender();
        String UniqueSubject=mail.getSubject();
        emailMainPage.clickWriteTheLetterButton()
                .typeAddressInput(mail.getRecipient())
                .typeSubjectInput(UniqueSubject)
                .typeBodyInput(mail.getBodyLetter())
                .clickSendLetterButton()
                .clickClosePanelLetterIsSent()
                .clickToMyselfFolder();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> subjectsOfLettersInToMyselfFolder=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        boolean isLetterPresentInToMyselfFolder=false;
        int indexOfLetterInToMyselfFolder = -1;
        for(int i=0;i<subjectsOfLettersInToMyselfFolder.size();i++){
            if (UniqueSubject.equals(subjectsOfLettersInToMyselfFolder.get(i).getText())){
                isLetterPresentInToMyselfFolder=true;
                indexOfLetterInToMyselfFolder=i+1;
            }
        }
        if (indexOfLetterInToMyselfFolder>0){
            emailMainPage.selectCheckboxOfLetterByIndex(indexOfLetterInToMyselfFolder)
                    .clickDeleteTheLetterButton();
        }
        emailMainPage.clickSentFolder();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> subjectsOfLettersInSentFolder=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        boolean isLetterPresentInSentFolder=false;
        int indexOfLetterInSentFolder = -1;
        for(int i=0;i<subjectsOfLettersInSentFolder.size();i++){
            if (subjectsOfLettersInSentFolder.get(i).getText().equals("Self: "+UniqueSubject)){
                isLetterPresentInSentFolder=true;
                indexOfLetterInSentFolder=i+1;
            }
        }
        if (indexOfLetterInSentFolder>0) {
            emailMainPage.selectCheckboxOfLetterByIndex(indexOfLetterInSentFolder)
                    .clickDeleteTheLetterButton();
        }
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(isLetterPresentInToMyselfFolder, "Letter isn't present in ToMyself Folder");
        anAssert.assertTrue(isLetterPresentInSentFolder, "Letter isn't present in Sent Folder");
        anAssert.assertAll();
    }

    @Test
    public void sendingLetterWithInvalidAddress(){
        Mail mail= MailFactory.getMailWithInvalidAddress();
        emailMainPage.clickWriteTheLetterButton()
                .typeAddressInput(mail.getRecipient())
                .typeSubjectInput(mail.getSubject())
                .typeBodyInput(mail.getBodyLetter())
                .clickSendLetterButton();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(emailMainPage.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(emailMainPage.getErrorMessageText(), "Присутствуют некорректные адреса");
        anAssert.assertTrue(emailMainPage.isErrorMessageToChangeTheLetterDisplayed(), "Second error message isn't displayed");
        anAssert.assertEquals(emailMainPage.getErrorMessageToChangeTheLetterText(), "Исправьте и попробуйте отправить заново");
        anAssert.assertAll();
        driver.findElement(By.xpath("//button[@data-test-id='false']")).click();
        driver.findElement(By.xpath("//span[@tabindex='590']")).click();
    }

    @Test
    public void sendingLetterWithoutSubjectAndBody() {
        Mail mail = MailFactory.getMailWithoutSubjectAndBody();
        emailMainPage.clickWriteTheLetterButton()
                .typeAddressInput(mail.getRecipient())
                .clickSendLetterButton()
                .clickSendEmptyLetterButton()
                .clickClosePanelLetterIsSent()
                .clickToMyselfFolder();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> subjectsOfLettersInToMyselfFolder=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        boolean isLetterPresentInToMyselfFolder=false;
        int indexOfLetterInToMyselfFolder = -1;
        for(int i=0;i<subjectsOfLettersInToMyselfFolder.size();i++){
            if (subjectsOfLettersInToMyselfFolder.get(i).getText().equals("<Без темы>")){
                isLetterPresentInToMyselfFolder=true;
                indexOfLetterInToMyselfFolder=i+1;
            }
        }
        if (indexOfLetterInToMyselfFolder>0){
            emailMainPage.selectCheckboxOfLetterByIndex(indexOfLetterInToMyselfFolder)
                    .clickDeleteTheLetterButton();
        }
        emailMainPage.clickSentFolder();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> subjectsOfLettersInSentFolder=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        boolean isLetterPresentInSentFolder=false;
        int indexOfLetterInSentFolder = -1;
        for(int i=0;i<subjectsOfLettersInSentFolder.size();i++) {
            if (subjectsOfLettersInSentFolder.get(i).getText().equals("Self:")){
                isLetterPresentInSentFolder=true;
                indexOfLetterInSentFolder=i+1;
            }
        }
        if (indexOfLetterInSentFolder>0) {
            emailMainPage.selectCheckboxOfLetterByIndex(indexOfLetterInSentFolder)
                    .clickDeleteTheLetterButton();
        }
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(isLetterPresentInToMyselfFolder, "Letter isn't present in ToMyself Folder");
        anAssert.assertTrue(isLetterPresentInSentFolder, "Letter isn't present in Sent Folder");
        anAssert.assertAll();
    }

    @Test
    public void deletingDraftLetter(){
        Mail mail= MailFactory.getMailWhereRecipientEqualsSender();
        String UniqueSubject=mail.getSubject();
        emailMainPage.clickWriteTheLetterButton()
                .typeAddressInput(mail.getRecipient())
                .typeSubjectInput(UniqueSubject)
                .typeBodyInput(mail.getBodyLetter())
                .clickSaveDraftMailButton()
                .clickCloseDraftMailButton()
                .clickDraftsFolder();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> subjectsOfLettersInDraftsFolder=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        boolean isLetterPresentInDraftsFolder=false;
        int indexOfLetterInDraftsFolder = -1;
        for(int i=0;i<subjectsOfLettersInDraftsFolder.size();i++){
            if (subjectsOfLettersInDraftsFolder.get(i).getText().equals(UniqueSubject)){
                isLetterPresentInDraftsFolder=true;
                indexOfLetterInDraftsFolder=i+1;
            }
        }
        if (indexOfLetterInDraftsFolder>0){
            emailMainPage.selectCheckboxOfLetterByIndex(indexOfLetterInDraftsFolder)
                    .clickDeleteTheLetterButton();
        }
        emailMainPage.clickTrashFolder();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> subjectsOfLettersInTrashFolder=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
        boolean isLetterPresentInTrashFolder=false;
        int indexOfLetterInTrashFolder = -1;
        for(int i=0;i<subjectsOfLettersInTrashFolder.size();i++) {
            if (subjectsOfLettersInTrashFolder.get(i).getText().equals(UniqueSubject)){
                isLetterPresentInTrashFolder=true;
                indexOfLetterInTrashFolder=i+1;
            }
        }
        if (indexOfLetterInTrashFolder>0) {
            emailMainPage.selectCheckboxOfLetterByIndex(indexOfLetterInTrashFolder)
                    .clickDeleteTheLetterButton();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isLetterPresentInTrashFolderAfterDeleting=false;
        if (isLetterPresentInTrashFolder){
            List<WebElement> subjectsOfLettersInTrashFolderAfterDeleting=driver.findElements(By.xpath("//span[@class='ll-sj__normal']"));
            for(int i=0;i<subjectsOfLettersInTrashFolderAfterDeleting.size();i++) {
                if (subjectsOfLettersInTrashFolderAfterDeleting.get(i).getText().equals(UniqueSubject)){
                    isLetterPresentInTrashFolderAfterDeleting=true;
                }
            }
        }
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(isLetterPresentInTrashFolder, "Letter isn't present in Trash Folder");
        anAssert.assertFalse(isLetterPresentInTrashFolderAfterDeleting, "Letter wasn't deleted from Trash Folder");
        anAssert.assertAll();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
