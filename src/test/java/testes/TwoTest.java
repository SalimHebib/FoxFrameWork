package testes;

import Resources.CommonApi;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class TwoTest extends CommonApi {

    public WebDriver driver;

    @Test(enabled = true)
    public void testTwo() throws IOException, InterruptedException {

        System.out.println("testTwo");
        driver = InitializeDriver();
        Thread.sleep(2000);



    }
}
