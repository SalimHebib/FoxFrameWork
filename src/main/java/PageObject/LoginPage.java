package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//*[@id='input-email']")
    private WebElement emailAddressField;

    @FindBy(xpath = "//*[@id='input-password']")
    private WebElement passWordField;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement loginButton;


    public WebElement emailAddressField(){
        return emailAddressField;
    }

    public WebElement passWordField(){
        return passWordField;
    }

    public WebElement loginButton(){
        return loginButton;
    }
}
