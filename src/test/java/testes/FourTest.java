package testes;

import Resources.CommonApi;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FourTest extends CommonApi {

    public WebDriver driver;

    @Test(enabled = false)
    public void testFour() throws IOException, InterruptedException {

        System.out.println("testFour ");

        driver = InitializeDriver();
        driver.get("http://tutorialsninja.com/demo/");
        Thread.sleep(2000);
        Assert.assertTrue(false);



    }

}
