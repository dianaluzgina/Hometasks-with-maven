package task6.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailMainPage extends BasePage {
    public EmailMainPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/compose/']")
    private WebElement writeTheLetterButton;
    @FindBy(xpath = "//a[@href='/inbox/']")
    private WebElement inboxFolder;
    @FindBy(xpath="//a[@href='/tomyself/']")
    private WebElement toMyselfFolder;
    @FindBy(xpath ="//a[@href='/sent/']")
    private WebElement sentFolder;
    @FindBy(xpath="//a[@href='/trash/']")
    private WebElement trashFolder;
    @FindBy(xpath = "//a[@href='/drafts/']")
    private WebElement draftsFolder;

    @FindBy(xpath = "//div[contains(@class,'head_container')]//input")
    private WebElement addressInput;
    @FindBy(xpath = "//div[contains(@class,'subject__container')]//input")
    private WebElement subjectInput;
    @FindBy(xpath = "//div[@tabindex='505']")
    private WebElement bodyInput;
    @FindBy(xpath = "//span[@data-title-shortcut='Ctrl+Enter']")
    private WebElement sendLetterButton;
    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement closePanelLetterIsSent;
    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendEmptyLetterButton;
    @FindBy(xpath = "//span[@title='Сохранить']")
    private WebElement saveDraftMailButton;
    @FindBy(xpath = "//button[@title='Закрыть']")
    private WebElement closeDraftMailButton;
    @FindBy(xpath = "//h1[@data-test-id='caption']")
    private WebElement errorMessage;
    @FindBy(css = "h1[data-test-id='caption']+div")
    private WebElement errorMessageToChangeTheLetter;

    public EmailMainPage clickWriteTheLetterButton(){
        defaultWait.until(ExpectedConditions.visibilityOf(writeTheLetterButton));
        writeTheLetterButton.click();
        return this;
    }

    public EmailMainPage clickInboxFolder(){
        defaultWait.until(ExpectedConditions.visibilityOf(inboxFolder));
        inboxFolder.click();
        return this;
    }

    public boolean isInboxFolderDisplayed() {
        defaultWait.until(ExpectedConditions.visibilityOf(inboxFolder));
        return inboxFolder.isDisplayed();
    }

    public EmailMainPage clickToMyselfFolder(){
        defaultWait.until(ExpectedConditions.visibilityOf(toMyselfFolder));
        toMyselfFolder.click();
        return this;
    }

    public EmailMainPage clickSentFolder(){
        defaultWait.until(ExpectedConditions.visibilityOf(sentFolder));
        sentFolder.click();
        return this;
    }

    public EmailMainPage typeAddressInput(String address) {
        defaultWait.until(ExpectedConditions.visibilityOf(addressInput));
        addressInput.sendKeys(address);
        return this;
    }

    public EmailMainPage typeSubjectInput(String subject) {
        subjectInput.sendKeys(subject);
        return this;
    }

    public EmailMainPage typeBodyInput(String body) {
        bodyInput.sendKeys(body);
        return this;
    }

    public EmailMainPage clickSendLetterButton(){
        sendLetterButton.click();
        return this;
    }

    public EmailMainPage clickClosePanelLetterIsSent(){
        defaultWait.until(ExpectedConditions.elementToBeClickable(closePanelLetterIsSent));
       closePanelLetterIsSent.click();
        return this;
    }

    public EmailMainPage clickSendEmptyLetterButton(){
        defaultWait.until(ExpectedConditions.visibilityOf(sendEmptyLetterButton));
        sendEmptyLetterButton.click();
        return this;
    }

    public EmailMainPage clickDraftsFolder(){
        defaultWait.until(ExpectedConditions.visibilityOf(draftsFolder));
        draftsFolder.click();
        return this;
    }

    public boolean isErrorMessageDisplayed(){
        defaultWait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }

    public boolean isErrorMessageToChangeTheLetterDisplayed(){
        defaultWait.until(ExpectedConditions.visibilityOf(errorMessageToChangeTheLetter));
        return errorMessageToChangeTheLetter.isDisplayed();
    }

    public String getErrorMessageToChangeTheLetterText(){
        return errorMessageToChangeTheLetter.getText();
    }

    public EmailMainPage selectCheckboxOfLetterByIndex(int index){
        driver.findElement(By.xpath("(//button[contains(@class,'ll-av_size_common')])["+index+"]")).click();
        return this;
    }

    public EmailMainPage clickDeleteTheLetterButton(){
        driver.findElement(By.xpath("//span[@data-title-shortcut='Del']")).click();
        return this;
    }

    public EmailMainPage clickLinkOfTheLetterByIndex(int index){
        driver.findElement(By.xpath("(//div[@class='dataset__items']/a[not(contains(@class, 'spinner'))])["+index+"]")).click();
        return this;
    }

    public EmailMainPage clickSaveDraftMailButton(){
        saveDraftMailButton.click();
        return this;
    }

    public EmailMainPage clickCloseDraftMailButton(){
        closeDraftMailButton.click();
        return this;
    }

    public EmailMainPage clickTrashFolder(){
        trashFolder.click();
        return this;
    }

}
