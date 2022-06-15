package practice.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

    public WebDriver driver;
    private static WebDriver chromeDriver;
    public WebDriverWait wait;

    public static WebDriver getChromeDriver() {
        if (chromeDriver == null) {
            WebDriverManager.chromedriver().setup();
            chromeDriver = new ChromeDriver();
            chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            chromeDriver.manage().window().maximize();
        }
        return chromeDriver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public BasePage () {
        driver = getChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getProperty("wait"))));
        PageFactory.initElements(driver, this);
    }

    public static String getProperty(String property) {
        Properties properties;
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/properties/env.properties");
            properties.load(fileInputStream);
        } catch(FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
        return properties.getProperty(property);
    }

    public void openSampleHost(String url) {
        driver.navigate().to(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
