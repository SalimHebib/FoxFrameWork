package Resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class CommonApi {

    public WebDriver driver;
    public Properties prop;

    public WebDriver InitializeDriver() throws IOException {

        prop = new Properties();
        String propPath = System.getProperty("user.dir") + "\\config.properties";
        FileInputStream fis = new FileInputStream(propPath);
        prop.load(fis);


        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver;

    }

    public String takeScreenshot(String testName, WebDriver driver) throws IOException {

        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);

        File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + df.format(date) + ".jpeg";
        FileUtils.copyFile(SourceFile, new File(destinationFilePath));
        return destinationFilePath;
    }

    @AfterMethod
    public void closure() {
        driver.close();

    }
}
