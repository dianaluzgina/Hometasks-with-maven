package task9.screens;

import task9.entities.Browser;

public class BasePage {

  protected Browser browser;

  protected BasePage() {
    browser = Browser.getInstance();
  }
}

