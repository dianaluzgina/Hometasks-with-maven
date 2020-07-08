package task7.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MailRuCloudMainPage extends BasePage{
    private static final String USER_ID_XPATH = "//i[@id='PH_user-email']";
    private static final String CLOUD_BUTTON_XPATH = "//span[@data-name='home']//a[@href='/home/']";
    private static final String CREATE_BUTTON_XPATH = "//div[@data-name='create']";
    private static final String CREATE_FOLDER_BUTTON_XPATH = "//div[@data-name='createFolder']";
    private static final String SELECT_ALL_BUTTON_XPATH = "//div[@id='cloud_toolbars']//div[@data-name='selectAll']";
    private static final String DELETE_BUTTON_XPATH = "//div[@data-name='remove']";
    private static final String APPROVE_DELETE_BUTTON_XPATH = "//button[@data-blockid='btn'][@data-name='remove']";
    private static final String CLOSE_INFORMING_MESSAGE_BUTTON_XPATH = "(//button[@data-blockid='btn'][@data-name='close'])[5]";
    private static final String ELEMENTS_IN_THE_CLOUD_OR_FOLDER_XPATH = "//div[@role='rowgroup']//a[@href='#']//div[contains(@class,'Name__name')]";

    private static final String FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH = "(//div[@role='rowgroup']//a[@href='#'])[1]";
    private static final String SECOND_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH = "(//div[@role='rowgroup']//a[@href='#'])[2]";
    private static final String DINAMIC_ELEMENT_IN_THE_FIRST_ELEMENT_XPATH =
            "//div[@role='rowgroup']//a[@href='#']//div[@class='DataListItem__dropNotify--1JYEP']";
    private static final String LIST_OF_FOLDERS_IN_THE_LEFT_PANEL_XPATH =
            "//div[@class='b-nav__group__list']//div[not(@data-storage='attaches')]//span[@class='b-nav__item__text']";
    private static final String FIRST_FOLDER_IN_THE_LEFT_PANEL_XPATH =
            "(//div[@class='b-nav__group__list']//div[not(@data-storage='attaches')]//span[@class='b-nav__item__text'])[1]";

    private static final String INPUT_NAME_OF_THE_FOLDER_XPATH = "//input[@placeholder='Введите имя папки']";
    private static final String CREATE_FOLDER_BUTTON_IN_THE_FRAME_XPATH = "//button[@class='ui fluid primary button']";
    private static final String LOAD_BUTTON_XPATH = "//div[@id='cloud_toolbars']//div[@data-name='upload']";
    private static final String LOAD_FILE_BUTTON_XPATH = "//input[@class='drop-zone__input']";
    private static final String DOWNLOADING_STATUS_MESSAGE_XPATH = "//span[@class='b-upload-status b-upload-panel__header__status js-upload-panel-header-status']";
    private static final String MOVE_BUTTON_XPATH = "//button[@data-name='move']";
    private static final String FOLDER_IS_EMPTY_MESSAGE_XPATH = "//div[@class='Empty__title--2pf4n']";
    private static final String SHARE_LINK_CONTEXT_MENU_XPATH = "//div[contains(@class,'DropdownList')][@data-name='publish']";
    private static final String SEND_LINK_BY_MAIL_BUTTON_XPATH = "//button[contains(@class,'ui button')][@title='Отправить']";
    private static final String NAME_OF_THE_PUBLIC_FOLDER_XPATH = "//span[@class='breadcrumbs__item__plain-header']";

    private static final String DOWNLOADING_COMPLETE_STATUS = "Загрузка завершена";

    public String getUserID(){
        browser.waitForVisibility(By.xpath(USER_ID_XPATH), browser.DEFAULT_TIMEOUT);
        return browser.getTextFrom(By.xpath(USER_ID_XPATH));
    }

    public MailRuCloudMainPage clickCreateButton(){
        browser.waitForVisibility(By.xpath(CREATE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(CREATE_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickCreateFolderButton(){
        browser.waitForVisibility(By.xpath(CREATE_FOLDER_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(CREATE_FOLDER_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickSelectAllButton(){
        browser.waitForElementToBeClickable(By.xpath(SELECT_ALL_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(SELECT_ALL_BUTTON_XPATH));
        return this;
    }

        public MailRuCloudMainPage clickDeleteButton(){
        browser.waitForVisibility(By.xpath(DELETE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(DELETE_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickApproveDeleteButton(){
        browser.waitForVisibility(By.xpath(APPROVE_DELETE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(APPROVE_DELETE_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickCloseInformingMessageButton(){
        browser.waitForVisibility(By.xpath(CLOSE_INFORMING_MESSAGE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(CLOSE_INFORMING_MESSAGE_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickCloudButton(){
        browser.waitForElementToBeClickable(By.xpath(CLOUD_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(CLOUD_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage typeNameOfTheFolder(String name) {
        browser.typeTo(By.xpath(INPUT_NAME_OF_THE_FOLDER_XPATH), name);
        return this;
    }

    public MailRuCloudMainPage clickCreateFolderButtonInTheFrame(){
        browser.waitForVisibility(By.xpath(CREATE_FOLDER_BUTTON_IN_THE_FRAME_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(CREATE_FOLDER_BUTTON_IN_THE_FRAME_XPATH));
        return this;
    }

    public String getNameOfTheFirstFolderInTheLeftPanel(){
        browser.waitForVisibility(By.xpath(FIRST_FOLDER_IN_THE_LEFT_PANEL_XPATH), browser.DEFAULT_TIMEOUT);
        return browser.getTextFrom(By.xpath(FIRST_FOLDER_IN_THE_LEFT_PANEL_XPATH));
    }

    public MailRuCloudMainPage switchToTheTabByIndex(int index){
        ArrayList<String> tabs = new ArrayList<String> (browser.getWindowHandles());
        browser.switchToTab(tabs.get(index-1));
        return this;
    }

    public MailRuCloudMainPage clickLoadButton(){
        browser.waitForVisibility(By.xpath(LOAD_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(LOAD_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage typePathToTheFileForDownloading(String pathToTheFile) {
        browser.waitForPresence(By.xpath(LOAD_FILE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.typeTo(By.xpath(LOAD_FILE_BUTTON_XPATH), pathToTheFile);
        return this;
    }

    public boolean isDownloadingOfFileComplete(){
        browser.waitForTextInElementLocated(By.xpath(DOWNLOADING_STATUS_MESSAGE_XPATH), browser.VERY_LONG_TIMEOUT, DOWNLOADING_COMPLETE_STATUS);
        return browser.getTextFrom(By.xpath(DOWNLOADING_STATUS_MESSAGE_XPATH)).equals(DOWNLOADING_COMPLETE_STATUS);
    }

    public MailRuCloudMainPage clickMoveButton(){
        browser.waitForVisibility(By.xpath(MOVE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(MOVE_BUTTON_XPATH));
        return this;
    }

    public List<WebElement> getElementsInTheCloudOrFolder(){
        return browser.findElements(By.xpath(ELEMENTS_IN_THE_CLOUD_OR_FOLDER_XPATH));
    }

    public List<WebElement> getListOfFoldersInTheLeftPanel(){
        return browser.findElements(By.xpath(LIST_OF_FOLDERS_IN_THE_LEFT_PANEL_XPATH));
    }

    public MailRuCloudMainPage cleanCloudBeforeTest() {
        MailRuCloudMainPage page = new MailRuCloudMainPage();
        List<WebElement> elementsInTheCloud = page.getElementsInTheCloudOrFolder();
        if (elementsInTheCloud.size() > 0) {
            page.clickDeleteButton()
                    .clickApproveDeleteButton()
                    .clickCloseInformingMessageButton();
        }
        return this;
    }

    public MailRuCloudMainPage cleanCloudAfterTest() {
        MailRuCloudMainPage page = new MailRuCloudMainPage();
        List<WebElement> elementsInTheCloud = page.getElementsInTheCloudOrFolder();
        if (elementsInTheCloud.size() > 0) {
            page.clickDeleteButton()
                    .clickApproveDeleteButton();
        }
        return this;
    }

    public MailRuCloudMainPage dragAndDropSecondElementIntoFirst(){
        browser.waitForVisibility(By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        browser.waitForVisibility(By.xpath(SECOND_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        browser.dragAndDrop(By.xpath(SECOND_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH),
                By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), By.xpath(DINAMIC_ELEMENT_IN_THE_FIRST_ELEMENT_XPATH),
                browser.DEFAULT_TIMEOUT);
        return this;
    }

    public MailRuCloudMainPage dragAndDropSecondElementByCoordinates(){
        browser.waitForVisibility(By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        browser.waitForVisibility(By.xpath(SECOND_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        browser.dragAndDropByCoordinates(By.xpath(SECOND_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH),
               -170,0, By.xpath(DINAMIC_ELEMENT_IN_THE_FIRST_ELEMENT_XPATH), browser.DEFAULT_TIMEOUT);
        return this;
    }

    public MailRuCloudMainPage doubleClickToTheFirstElement(){
        browser.waitForElementToBeClickable(By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        browser.doubleClickElement(By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH));
        return this;
    }

    public boolean isMessageThatFolderIsEmptyVisible(){
        browser.waitForVisibility(By.xpath(FOLDER_IS_EMPTY_MESSAGE_XPATH), browser.DEFAULT_TIMEOUT);
        return browser.isDisplayed(By.xpath(FOLDER_IS_EMPTY_MESSAGE_XPATH));
    }

    public MailRuCloudMainPage contextClickOnTheFirstElement(){
        browser.waitForElementToBeClickable(By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        browser.contextClickElement(By.xpath(FIRST_ELEMENT_IN_THE_CLOUD_OR_FOLDER_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickShareLink(){
        browser.waitForElementToBeClickable(By.xpath(SHARE_LINK_CONTEXT_MENU_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(SHARE_LINK_CONTEXT_MENU_XPATH));
        return this;
    }

    public MailRuCloudMainPage clickSendLinkBYMail(){
        browser.waitForElementToBeClickable(By.xpath(SEND_LINK_BY_MAIL_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        browser.clickElement(By.xpath(SEND_LINK_BY_MAIL_BUTTON_XPATH));
        return this;
    }

    public MailRuCloudMainPage loadUrl(String Url){
        browser.getUrl(Url);
        return this;
    }

    public String getNameOfThePublicFolder(){
        browser.waitForVisibility(By.xpath(NAME_OF_THE_PUBLIC_FOLDER_XPATH), browser.DEFAULT_TIMEOUT);
        return browser.getTextFrom(By.xpath(NAME_OF_THE_PUBLIC_FOLDER_XPATH));
    }

    public MailRuCloudMainPage pressEscOnThePage(){
        browser.pressEscOnThePage();
        browser.waitForStalenessOfElement(By.xpath(SEND_LINK_BY_MAIL_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
        return this;
    }

    public MailRuCloudMainPage pressCtrlAOnThePage(){
        browser.pressCtrlAOnThePage();
        return this;
    }

}
