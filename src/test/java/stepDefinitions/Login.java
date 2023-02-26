package stepDefinitions;

import PageObject.AccountPage;
import PageObject.LandingPage;
import PageObject.LoginPage;
import Resources.CommonApi;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;


public class Login extends CommonApi {

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Given("Open any Browser")
    public void openAnyBrowser() throws IOException {

        driver = InitializeDriver();
    }

    @And("Navigate to Login page")
    public void navigateToLoginPage() throws InterruptedException {

        driver.get(prop.getProperty("url"));
        landingPage = new LandingPage(driver);
        landingPage.myAccountDropdown().click();
        landingPage.loginOption().click();
        Thread.sleep(3000);

    }

    @When("User enters username as {string} and password as {string} into the fields")
    public void userEntersUsernameAsAndPasswordAsIntoTheFields(String email, String password) {

        loginPage = new LoginPage(driver);
        loginPage.emailAddressField().sendKeys(email);
        loginPage.passWordField().sendKeys(password);

    }

    @And("User clicks on Login button")
    public void userClicksOnLoginButton() {

        accountPage = new AccountPage(driver);
        loginPage.loginButton().click();
    }

    @Then("Verify user is able to successfully login")
    public void verifyUserIsAbleToSuccessfullyLogin() {

        Assert.assertTrue(accountPage.editInformation().isDisplayed());
    }


    @After
    public void closeBrowser() {
        driver.quit();
    }
}
