package task11.screens;


import org.openqa.selenium.By;

public class WriteNewEmailPage extends EmailMainPage {
    private static final String ADDRESS_INPUT_XPATH = "//div[contains(@class,'head_container')]//input";
    private static final String SUBJECT_INPUT_XPATH = "//div[contains(@class,'subject__container')]//input";
    private static final String BODY_INPUT_XPATH = "//div[@tabindex='505']";
    private static final String SEND_MAIL_BUTTON_XPATH = "//span[@data-title-shortcut='Ctrl+Enter']";
    private static final String CLOSE_PANEL_THE_MAIL_IS_SENT_XPATH = "//span[@title='Закрыть']";
    private static final String SEND_EMPTY_MAIL_BUTTON_XPATH = "//span[contains(text(),'Отправить')]";
    private static final String SAVE_DRAFT_MAIL_BUTTON_XPATH = "//span[@title='Сохранить']";
    private static final String CLOSE_DRAFT_MAIL_BUTTON_XPATH = "//button[@title='Закрыть']";
    private static final String ERROR_MESSAGE_XPATH = "//h1[@data-test-id='caption']";
    private static final String ERROR_MESSAGE_TO_CHANGE_THE_MAIL_CSS = "h1[data-test-id='caption']+div";
    private static final String BODY_WITH_LINK_TO_PUBLIC_FOLDER_XPATH = "(//div[@tabindex='505']/div)[1]";

    public WriteNewEmailPage typeAddressInput(String address) {
        browser.waitForVisibility(By.xpath(ADDRESS_INPUT_XPATH), browser.DEFAULT_TIMEOUT);
        browser.typeTo(By.xpath(ADDRESS_INPUT_XPATH), address);
        return this;
    }

    public WriteNewEmailPage typeSubjectInput(String subject) {
        browser.typeTo(By.xpath(SUBJECT_INPUT_XPATH), subject);
        return this;
    }

    public WriteNewEmailPage typeBodyInput(String body) {
        browser.typeTo(By.xpath(BODY_INPUT_XPATH), body);
        return this;
    }

    public WriteNewEmailPage clickSendMailButton(){
        browser.clickElement(By.xpath(SEND_MAIL_BUTTON_XPATH));
        return this;
    }

    public WriteNewEmailPage clickClosePanelMailIsSent(){
        browser.waitForElementToBeClickable(By.xpath(CLOSE_PANEL_THE_MAIL_IS_SENT_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(CLOSE_PANEL_THE_MAIL_IS_SENT_XPATH));
        return this;
    }

    public WriteNewEmailPage clickSendEmptyMailButton(){
        browser.waitForVisibility(By.xpath(SEND_EMPTY_MAIL_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(SEND_EMPTY_MAIL_BUTTON_XPATH));
        return this;
    }

    public boolean isErrorMessageDisplayed(){
        browser.waitForVisibility(By.xpath(ERROR_MESSAGE_XPATH), browser.DEFAULT_TIMEOUT);
        return browser.isDisplayed(By.xpath(ERROR_MESSAGE_XPATH));
    }

    public String getErrorMessageText(){
        return browser.getTextFrom(By.xpath(ERROR_MESSAGE_XPATH));
    }

    public boolean isErrorMessageToChangeTheMailDisplayed(){
        browser.waitForVisibility(By.cssSelector(ERROR_MESSAGE_TO_CHANGE_THE_MAIL_CSS), browser.DEFAULT_TIMEOUT);
        return browser.isDisplayed(By.cssSelector(ERROR_MESSAGE_TO_CHANGE_THE_MAIL_CSS));
    }

    public String getErrorMessageToChangeTheMailText(){
        return browser.getTextFrom(By.cssSelector(ERROR_MESSAGE_TO_CHANGE_THE_MAIL_CSS));
    }

    public WriteNewEmailPage clickSaveDraftMailButton(){
        browser.clickElement(By.xpath(SAVE_DRAFT_MAIL_BUTTON_XPATH));
        return this;
    }

    public WriteNewEmailPage clickCloseDraftMailButton(){
        browser.clickElement(By.xpath(CLOSE_DRAFT_MAIL_BUTTON_XPATH));
        return this;
    }

    public String getTextOfTheBodyWithLinkToPublicFolder(){
        return browser.getTextFrom(By.xpath(BODY_WITH_LINK_TO_PUBLIC_FOLDER_XPATH));
    }
}
