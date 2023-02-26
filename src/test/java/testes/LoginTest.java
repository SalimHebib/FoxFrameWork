package testes;

import PageObject.AccountPage;
import PageObject.LandingPage;
import PageObject.LoginPage;
import Resources.CommonApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends CommonApi {

    public WebDriver driver;
    Logger log;

    @Test(dataProvider = "getLoginData")
    public void login(String email, String password, String expectedResult) {

        LandingPage landingPage = new LandingPage(driver);
        landingPage.myAccountDropdown().click();
        log.debug("Clicked on My Account dropdown");
        landingPage.loginOption().click();
        log.debug("Clicked on login option");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailAddressField().sendKeys(email);
        log.debug("Email addressed got entered");
        loginPage.passWordField().sendKeys(password);
        log.debug("Password got entered");
        loginPage.loginButton().click();
        log.debug("Clicked on Login Button");

        AccountPage accountPage = new AccountPage(driver);

        String actualResult = null;

        try {
            if (accountPage.editInformation().isDisplayed()) {
                log.debug("User got logged in");
                actualResult = "Successful";

            }
        } catch (Exception e) {
            log.debug("User didn't log in");
            actualResult = "Failure";

        }
        Assert.assertEquals(actualResult, expectedResult);

    }

    @BeforeMethod
    public void OpenApplication() throws IOException {

        log = LogManager.getLogger(LoginTest.class.getName());

        driver = InitializeDriver();
        log.debug("Browser got launched");
        driver.get(prop.getProperty("url"));
        log.debug("Navigated to application URL");
    }


    @DataProvider
    public Object[][] getLoginData() {

        Object[][] data = {{"dz@gmail.com", "Second@123", "Successful"}};



        return data;
    }


}