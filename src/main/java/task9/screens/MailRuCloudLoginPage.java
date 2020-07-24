package task9.screens;

import org.openqa.selenium.By;

public class MailRuCloudLoginPage extends BasePage {
    private static final String URL="https://cloud.mail.ru/";

    private static final String ENTER_TO_THE_CLOUD_BUTTON_XPATH =
            "//div[@class='content-block__buttons']/a[contains(@class,'button_main')]";
    private static final String USER_NAME_INPUT_XPATH = "//input[@name='username']";
    private static final String USER_DOMAIN_INPUT_XPATH = "//input[@name='Domain']";
    private static final String PASSWORD_BUTTON_XPATH = "//button[@data-test-id='next-button']";
    private static final String USER_PASSWORD_INPUT_XPATH = "//input[@name='password']";
    private static final String SUBMIT_BUTTON_XPATH =  "//button[@data-test-id='submit-button']";

    public MailRuCloudLoginPage load(){
        browser.getUrl(URL);
        return this;
    }

    public MailRuCloudLoginPage clickEnterToTheCloudButton(){
        browser.clickElement(By.xpath(ENTER_TO_THE_CLOUD_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudLoginPage switchToLoginFrame() {
        browser.switchToFrame(0);
        return this;
    }

    public MailRuCloudLoginPage typeUserName(String name) {
        browser.typeTo(By.xpath(USER_NAME_INPUT_XPATH), name);
        return this;
    }

    public MailRuCloudLoginPage selectDomain(String domainName){
        browser.typeTo(By.xpath(USER_DOMAIN_INPUT_XPATH), domainName);
        return this;
    }

    public MailRuCloudLoginPage clickPasswordButton(){
        browser.clickElement(By.xpath(PASSWORD_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudLoginPage typePassword(String password){
        browser.waitForVisibility(By.id(USER_PASSWORD_INPUT_XPATH), browser.DEFAULT_TIMEOUT);
        browser.typeTo(By.xpath(USER_PASSWORD_INPUT_XPATH), password);
        return this;
    }

    public MailRuCloudMainPage clickSubmitButton(){
        browser.clickElement(By.xpath(SUBMIT_BUTTON_XPATH));
        return new MailRuCloudMainPage();
    }
}
