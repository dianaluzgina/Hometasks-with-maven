package task11.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class EmailMainPage extends BasePage {

  private static final String WRITE_THE_MAIL_BUTTON_XPATH = "//a[@href='/compose/']";
  private static final String TO_MYSELF_FOLDER_XPATH = "//a[@href='/tomyself/']";
  private static final String SENT_FOLDER_XPATH = "//a[@href='/sent/']";
  private static final String TRASH_FOLDER_XPATH = "//a[@href='/trash/']";
  private static final String DRAFTS_FOLDER_XPATH = "//a[@href='/drafts/']";

  private static final String DELETE_THE_MAIL_BUTTON_XPATH = "//span[@data-title-shortcut='Del']";
  private static final String CHECKBOX_OF_MAIL_XPATH = "(//button[contains(@class,'ll-av_size_common')])";
  private static final String USER_ID_XPATH = "//i[@id='PH_user-email']";
  private static final String SELECT_ALL_MAILS_BUTTON_XPATH = "//span[@data-title-shortcut='Ctrl+A']";
  private static final String SUBJECT_OF_FIRST_MAIL_XPATH = "(//span[@class='ll-sj__normal'])[1]";
  private static final String LIST_OF_SUBJECTS_OF_MAILS_XPATH = "//span[@class='ll-sj__normal']";
  private static final String CLEAR_TRASH_FOLDER_BUTTON_XPATH = "//a[@rel='noopener noreferer']";
  private static final String APPROVE_CLEARING_TRASH_FOLDER_BUTTON_XPATH = "//div[@class='layer__submit-button']";

  private static final String CLOUD_BUTTON_XPATH = "//a[@href='https://cloud.mail.ru']";

  public EmailMainPage clickWriteTheMailButton() {
    browser.waitForVisibility(By.xpath(WRITE_THE_MAIL_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(WRITE_THE_MAIL_BUTTON_XPATH));
    return this;
  }

  public EmailMainPage clickToMyselfFolder() {
    browser.waitForVisibility(By.xpath(TO_MYSELF_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(TO_MYSELF_FOLDER_XPATH));
    return this;
  }

  public EmailMainPage clickSentFolder() {
    browser.waitForVisibility(By.xpath(SENT_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(SENT_FOLDER_XPATH));
    return this;
  }


  public EmailMainPage clickDraftsFolder() {
    browser.waitForVisibility(By.xpath(DRAFTS_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(DRAFTS_FOLDER_XPATH));
    return this;
  }


  public EmailMainPage selectCheckboxOfMailByIndex(int index) {
    browser.waitForVisibility(By.xpath(CHECKBOX_OF_MAIL_XPATH + "[" + index + "]"),
        browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(CHECKBOX_OF_MAIL_XPATH + "[" + index + "]"));
    return this;
  }

  public EmailMainPage clickDeleteTheMailButton() {
    browser.waitForVisibility(By.xpath(DELETE_THE_MAIL_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(DELETE_THE_MAIL_BUTTON_XPATH));
    return this;
  }


  public EmailMainPage clickTrashFolder() {
    browser.waitForVisibility(By.xpath(TRASH_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(TRASH_FOLDER_XPATH));
    return this;
  }

  public String getUserID() {
    browser.waitForVisibility(By.xpath(USER_ID_XPATH), browser.DEFAULT_TIMEOUT);
    return browser.getTextFrom(By.xpath(USER_ID_XPATH));
  }

  public EmailMainPage clickSelectAllMailsButton() {
    browser.waitForElementToBeClickable(By.xpath(SELECT_ALL_MAILS_BUTTON_XPATH),
        browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(SELECT_ALL_MAILS_BUTTON_XPATH));
    return this;
  }

  public String getTextOfSubjectOfFirstMail() {
    browser.waitForVisibility(By.xpath(SUBJECT_OF_FIRST_MAIL_XPATH), browser.DEFAULT_TIMEOUT);
    return browser.getTextFrom(By.xpath(SUBJECT_OF_FIRST_MAIL_XPATH));
  }

  public List<WebElement> getSubjectsOfMails() {
    return browser.findElements(By.xpath(LIST_OF_SUBJECTS_OF_MAILS_XPATH));
  }

  public EmailMainPage cleanFolder() {
    EmailMainPage page = new EmailMainPage();
    List<WebElement> subjectsOfMails = page.getSubjectsOfMails();
    if (subjectsOfMails.size() > 0) {
      page.pressCtrlAOnThePage()
          .pressDelOnThePage();
    }
    return this;
  }

  public MailRuCloudMainPage clickCloudButton() {
    browser.waitForVisibility(By.xpath(CLOUD_BUTTON_XPATH), browser.LONG_TIMEOUT);
    browser.clickElement(By.xpath(CLOUD_BUTTON_XPATH));
    return new MailRuCloudMainPage();
  }

  public EmailMainPage clickClearTrashFolderButton() {
    browser.waitForVisibility(By.xpath(CLEAR_TRASH_FOLDER_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(CLEAR_TRASH_FOLDER_BUTTON_XPATH));
    return this;
  }

  public EmailMainPage clickApproveClearingTrashFolderButton() {
    browser.waitForVisibility(By.xpath(APPROVE_CLEARING_TRASH_FOLDER_BUTTON_XPATH),
        browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(APPROVE_CLEARING_TRASH_FOLDER_BUTTON_XPATH));
    browser
        .waitForStalenessOfElement(By.xpath(SUBJECT_OF_FIRST_MAIL_XPATH), browser.DEFAULT_TIMEOUT);
    return this;
  }

  public EmailMainPage pressCtrlAOnThePage() {
    browser.pressCtrlAOnThePage();
    return this;
  }

  public EmailMainPage pressDelOnThePage() {
    browser.pressDelOnThePage();
    return this;
  }
}
