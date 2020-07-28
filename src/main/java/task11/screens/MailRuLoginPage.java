package task11.screens;

import org.openqa.selenium.By;

public class MailRuLoginPage extends BasePage {

  private static final String URL = "https://mail.ru/";

  private static final String USER_NAME_INPUT_ID = "mailbox:login";
  private static final String USER_DOMAIN_INPUT_ID = "mailbox:domain";
  private static final String PASSWORD_BUTTON_XPATH = "//input[@class='o-control']";
  private static final String USER_PASSWORD_INPUT_ID = "mailbox:password";
  private static final String SUBMIT_BUTTON_XPATH = "//input[@class='o-control']";
  private static final String ERROR_MESSAGE_ID = "mailbox:error";

  public MailRuLoginPage load() {
    browser.getUrl(URL);
    return this;
  }

  public MailRuLoginPage typeUserName(String name) {
    browser.typeTo(By.id(USER_NAME_INPUT_ID), name);
    return this;
  }

  public MailRuLoginPage selectDomain(String domainName) {
    browser.selectInElement(By.id(USER_DOMAIN_INPUT_ID), domainName);
    return this;
  }

  public MailRuLoginPage clickPasswordButton() {
    browser.clickElement(By.xpath(PASSWORD_BUTTON_XPATH));
    return this;
  }

  public MailRuLoginPage typePassword(String password) {
    browser.waitForVisibility(By.id(USER_PASSWORD_INPUT_ID), browser.DEFAULT_TIMEOUT);
    browser.typeTo(By.id(USER_PASSWORD_INPUT_ID), password);
    return this;
  }

  public EmailMainPage clickSubmitButton() {
    browser.clickElement(By.xpath(SUBMIT_BUTTON_XPATH));
    return new EmailMainPage();
  }

  public boolean isErrorMessageDisplayed() {
    browser.waitForVisibility(By.id(ERROR_MESSAGE_ID), browser.DEFAULT_TIMEOUT);
    return browser.isDisplayed(By.id(ERROR_MESSAGE_ID));
  }

  public String getErrorMessageText() {
    return browser.getTextFrom(By.id(ERROR_MESSAGE_ID));
  }
}
