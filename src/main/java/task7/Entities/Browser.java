package task7.Entities;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {
    public static final int VERY_LONG_TIMEOUT = 100;
    public static final int LONG_TIMEOUT = 30;
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int SHORT_TIMEOUT = 5;

    private WebDriver driver;
    private static Browser browser;

    private Browser() {
        System.setProperty("webdriver.chrome.driver",
                "./src/main/resources/task7/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    public static Browser getInstance() {
        if (browser == null) {
            return browser = new Browser();
        } else {
            return browser;
        }
    }

    public void closeBrowser() {
        driver.quit();
        browser = null;
    }

    public void switchToFrame(int index) {
        System.out.println(String.format("Switch to frame with index [%s]", index));
        driver.switchTo().frame(index);
    }

    public Set<String> getWindowHandles(){
        System.out.println("Find all open tabs in browser");
        return driver.getWindowHandles();
    }

    public void switchToTab(String nameOfTab) {
        System.out.println(String.format("Switch to tab [%s]", nameOfTab));
        driver.switchTo().window(nameOfTab);
    }


    public void getUrl(String url){
        System.out.println(String.format("Open page by URL: [%s], maximize page", url));
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void clickElement(By by){
        System.out.println(String.format("Click element located: [%s]", by));
        driver.findElement(by).click();
    }

    public void doubleClickElement(By by){
        System.out.println(String.format("DoubleClick element located: [%s]", by));
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(by)).build().perform();
    }

    public void contextClickElement(By by){
        System.out.println(String.format("ContextClick element located: [%s]", by));
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(by)).build().perform();
    }

    public void typeTo(By by, String value){
        System.out.println(String.format("Type [%s] to element located: [%s]", value, by));
        driver.findElement(by).sendKeys(value);
    }

    public void selectInElement(By by, String value){
        System.out.println(String.format("Select [%s] in element located: [%s]", value, by));
        Select select= new Select(driver.findElement(by));
        select.selectByVisibleText(value);
    }

    public boolean isDisplayed(By by){
        System.out.println(String.format("Check that element located [%s] is displayed", by));
        return driver.findElement(by).isDisplayed();
    }

    public String getTextFrom(By by){
        String text = driver.findElement(by).getText();
        System.out.println(String.format("Found [%s] on element located [%s]", text, by));
        return text;
    }

    public void waitForVisibility(By by, int seconds){
        System.out.println(String.format("Wait for visibility of element located [%s] for [%s] seconds",
                by, seconds));
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForPresence(By by, int seconds){
        System.out.println(String.format("Wait for presence of element located [%s] for [%s] seconds",
                by, seconds));
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForStalenessOfElement(By by, int seconds){
        System.out.println(String.format("Wait for staleness of element located [%s] for [%s] seconds",
                by, seconds));
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.stalenessOf(driver.findElement(by)));
    }

    public void waitForTextInElementLocated(By by, int seconds, String text){
        System.out.println(String.format("Wait for text [%s] in element located [%s] for [%s] seconds", text, by, seconds));
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public void waitForElementToBeClickable(By by, int seconds){
        System.out.println(String.format("Wait for element located [%s] to be clickable for [%s] seconds",
                by, seconds));
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    public void dragAndDropByCoordinates(By bySource, int pixelsHorizontal, int pixelsVertical, By byDinamicElement, int seconds){
        System.out.println(String.format("Drag and drop element located [%s] by pixels [%s] horizontal and pixels [%s] vertical",
                bySource, pixelsHorizontal, pixelsVertical));
        new Actions(driver).clickAndHold(driver.findElement(bySource)).build().perform();
        new Actions(driver).moveByOffset(pixelsHorizontal, pixelsVertical).build().perform();
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.presenceOfElementLocated(byDinamicElement));
        new Actions(driver).release().build().perform();
    }

    public void dragAndDrop(By bySource, By byTarget, By byDinamicElement, int seconds){
        System.out.println(String.format("Drag and drop element located [%s] to element located [%s]",
                bySource, byTarget));
        new Actions(driver).clickAndHold(driver.findElement(bySource)).moveToElement( driver.findElement(byTarget)).build().perform();
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.presenceOfElementLocated(byDinamicElement));
       new Actions(driver).release().perform();
    }

    public void pressEscOnThePage(){
        System.out.println("Press Esc on the page");
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
    }

    public void pressCtrlAOnThePage(){
        System.out.println("Press Ctrl+A on the page");
        driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,"a");
    }

    public void pressDelOnThePage(){
        System.out.println("Press Del on the page");
        driver.findElement(By.tagName("body")).sendKeys(Keys.DELETE);
    }
}