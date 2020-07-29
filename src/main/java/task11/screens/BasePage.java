package task11.screens;

import task11.entities.Browser;

public class BasePage {

  protected Browser browser;

  protected BasePage() {
    browser = Browser.getInstance();
  }
}

