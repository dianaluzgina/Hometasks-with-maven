package task6.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class MailRuLoginPage extends BasePage {
    private static final String URL="https://mail.ru/";

    @FindBy(id = "mailbox:login")
    private WebElement userNameInput;
    @FindBy(id ="mailbox:domain")
    private WebElement domainInput;
    @FindBy(xpath = "//input[@class='o-control']")
    private WebElement passwordButton;
    @FindBy(id="mailbox:password")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@class='o-control']")
    private WebElement submitButton;
    @FindBy(id="mailbox:error")
    private WebElement errorMessage;

    public MailRuLoginPage(WebDriver driver){
       super(driver);
    }
    public MailRuLoginPage load(){
        driver.get(URL);
        driver.manage().window().maximize();
        return this;
    }

    public MailRuLoginPage typeUserName(String name){
        userNameInput.sendKeys(name);
        return this;
    }

    public MailRuLoginPage selectDomain(String domainName){
        Select select= new Select(domainInput);
        select.selectByVisibleText(domainName);
        return this;
    }

    public MailRuLoginPage clickPasswordButton(){
        passwordButton.click();
        return this;
    }

    public MailRuLoginPage typePassword(String password){
        defaultWait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
     return this;
    }

    public EmailMainPage clickSubmitButton(){
        submitButton.click();
        return new EmailMainPage(driver);
    }

    public boolean isErrorMessageDisplayed(){
        defaultWait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }
}
