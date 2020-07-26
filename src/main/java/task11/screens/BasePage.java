package task11.screens;

import task11.entities.Browser;

public class BasePage {
    protected Browser browser;

    public BasePage(){
        browser = Browser.getInstance();
    }
}

