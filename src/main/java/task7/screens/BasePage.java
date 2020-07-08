package task7.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import task7.Entities.Browser;

public class BasePage {
    protected Browser browser;

    public BasePage(){
        browser = Browser.getInstance();
    }
}

