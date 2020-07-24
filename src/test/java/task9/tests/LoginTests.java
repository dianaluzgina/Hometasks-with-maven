package task9.tests;



import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import task9.TestListener;
import task9.entities.Browser;
import task9.screens.EmailMainPage;
import task9.screens.MailRuLoginPage;

@Listeners({TestListener.class})
public class LoginTests {
    private MailRuLoginPage page = new MailRuLoginPage();

    @Given("browser started")
    public void browserStarted() {
        Browser.getInstance();
    }

    @And("mail.ru page is loaded")
    public void mailRuPageIsLoaded() {
        page.load();
    }

    @When("I type {string} to id input")
    public void iTypeToIdInput(String arg0) {
        page.typeUserName(arg0);
    }

    @And("I select {string} in dropdown list")
    public void iSelectInDropdownList(String arg0) {
        page.selectDomain(arg0);
    }

    @And("I click password button")
    public void iClickPasswordButton() {
        page.clickPasswordButton();
    }

    @And("I type {string} to password input")
    public void iTypeToPasswordInput(String arg0) {
        page.typePassword(arg0);
    }

    @And("I click submit button")
    public void iClickSubmitButton() {
        page.clickSubmitButton();
    }

    @Then("I see {string}")
    public void iSee(String arg0) {
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(page.isErrorMessageDisplayed(), "Error message isn't displayed");
        anAssert.assertEquals(page.getErrorMessageText(), arg0,
                String.format("Error message on the page differs from message '%s'", arg0));
        anAssert.assertAll();
    }

    @Then("I see my {string} id")
    public void iSeeMyId(String arg0) {
        Assert.assertEquals(new EmailMainPage().getUserID(), arg0,
                "User id wasn't found!");
    }

    @After
    public void tearDown() {
        Browser.getInstance().closeBrowser();
    }

    @Given("browser  started")
    public void newBrowserStarted() {
        Browser.getInstance();
    }
}
