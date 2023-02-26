package testes;

import Resources.CommonApi;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class ThreeTest extends CommonApi {

    public WebDriver driver;

    @Test(enabled = true)
    public void testThree() throws IOException, InterruptedException {

        System.out.println("testThree");
        driver = InitializeDriver();
        Thread.sleep(2000);



    }
}
